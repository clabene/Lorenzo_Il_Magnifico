package network.client.socket;

import logic.actionSpaces.ActionSpace;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.resources.CouncilFavour;
import logic.utility.StaticVariables;
import network.ResponseCode;
import network.client.AbstractNetworkClient;
import network.client.ClientInterface;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IBM on 06/06/2017.
 */
public class SocketClient extends AbstractNetworkClient {

    private String ipAddress;
    private int port;
    private Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    private ClientStreamHandler streamHandler;

    public SocketClient(ClientInterface clientController,int port){
        super(clientController);
        this.ipAddress = "127.0.0.1";
        this.port = port;

        streamHandler = new ClientStreamHandler(input, this);
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            System.out.println("Could not connect ot the server");
        }
        initializeStreams();

        new SocketClientListener(input, this).start();
    }

    private void initializeStreams(){
        try {
            this.output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            output.flush();
            this.input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Could not initialize streams");
        }

    }


    private void notifyRequestHandleOutcome(){
        try {
            ResponseCode requestHandleOutcome = (ResponseCode) input.readObject();
            getClientController().showOutcome(requestHandleOutcome);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problems in notifying request output");
        }
    }

    @Override
    public void tryToLogIn() {
        try{
            output.writeObject("LOG_IN_REQUEST");
            output.writeObject(getId());
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send log in request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void tryToJoinGame() {
        try{
            output.writeObject("JOIN_GAME_REQUEST");
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send join game request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void tryToCreateRoom(int numberOfPlayers) {
        try{
            output.writeObject("CREATE_NEW_ROOM_REQUEST");
            output.writeObject(numberOfPlayers); //check if boxing is necessary
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send create new room request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        try {
            output.writeObject("FAMILY_MEMBER_SELECTION_REQUEST");
            output.writeObject(familyMember);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send family member request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void useSlaves(int quantity) {
        try {
            output.writeObject("USE_SLAVES_REQUEST");
            output.writeObject(quantity);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send family member request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void selectActionSpace(String actionSpaceId) {
        try {
            output.writeObject("ACTION_SPACE_SELECTION_REQUEST");
            output.writeObject(actionSpaceId);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send family member request");
        }
        notifyRequestHandleOutcome();
    }

    //@Override
    public void dealWithVatican(int periodNumber) {
        boolean notSupporting = takeVaticanDecision(periodNumber);

        try {
            output.writeObject(notSupporting);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send council favours indexes");
        }
        notifyRequestHandleOutcome();
    }

    private boolean takeVaticanDecision(int periodNumber){
        System.out.println("Do you want to support the Vatican?");
        System.out.println(getClientController().getView().getBoard().getTassels()[periodNumber]);
        System.out.println("0) Yes, 1) No");
        Scanner scanner = new Scanner(System.in);
        do {
            int notSupporting = scanner.nextInt();
            if (notSupporting == 1) return true;
            if (notSupporting == 0) return false;
        } while(true);
    }

    //@Override
    public void selectCouncilFavour(int numberOfFavours) {
        Gainable[] favours = selectFavours(numberOfFavours);

        try {
            output.writeObject(favours);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send council favours indexes");
        }
        notifyRequestHandleOutcome();
    }

    private Gainable[] selectFavours(int numberOfFavours){
        CouncilFavour councilFavour = new CouncilFavour(numberOfFavours);
        Scanner scanner = new Scanner(System.in);
        Gainable[] toReturn = new Gainable[numberOfFavours];

        System.out.println("Which favour do you want?");
        do {
            System.out.println(councilFavour);
            try {
                toReturn[toReturn.length] = StaticVariables.COUNCIL_FAVOURS[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No valid input given");
                numberOfFavours++;
            }
            numberOfFavours--; //todo check if i get here when exception is caught
        } while (numberOfFavours >= 0);

        return toReturn;
    }

    //@Override
    public void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        ActionSpace actionSpace = selectActionSpace(actionSpaces);
        try {
            output.writeObject(actionSpace);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send action space for extra action");
        }
        notifyRequestHandleOutcome();
    }

    private ActionSpace selectActionSpace(ArrayList<ActionSpace> actionSpaces){
        System.out.println("What action space do you want to use?");
        int i = 0;
        for(ActionSpace tmp : actionSpaces) {
            i++;
            System.out.println(i + ") " +tmp);
        }
        Scanner scanner = new Scanner(System.in);
        i = scanner.nextInt();
        try {
            return actionSpaces.get(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return actionSpaces.get(actionSpaces.size()); //todo have user to write another number
        }
    }

    @Override
    public void leaveGame() {

    }


    public class SocketClientListener extends Thread {

        private ObjectInputStream input;
        private SocketClient socketClient;

        public SocketClientListener(ObjectInputStream input, SocketClient socketClient){
            this.input = input;
            this.socketClient = socketClient;
        }

        @Override
        public void run(){
            try {
                while (true) {
                    String serverRequestCode = (String) input.readObject();
                    streamHandler.respond(serverRequestCode);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Could not have ServerStreamHandler started");
            }
        }

    }

}
