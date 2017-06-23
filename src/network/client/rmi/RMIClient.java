package network.client.rmi;

import com.google.gson.Gson;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.utility.StaticVariables;
import network.ResponseCode;
import network.client.AbstractNetworkClient;
import network.client.ClientInterface;
import network.client.rmi.RMIClientInterface;
import network.server.rmi.RMIServerInterface;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


/**
 * Created by IBM on 06/06/2017.
 */
public class RMIClient extends AbstractNetworkClient implements RMIClientInterface,Serializable{
    private transient String ipAddress;
    private transient int port;
    private transient RMIServerInterface rmiServerInterface;
    private  transient ClientInterface clientController;



    public RMIClient(ClientInterface clientController, int port){
        this.ipAddress = "127.0.0.1";
        this.port = port;
        this.clientController = clientController;
    }

    @Override
    public ClientInterface getClientController() {
        return clientController;
    }

    //    @Override
    public void connect() {
        //connect to RMIServer
        try{
            Registry registry = LocateRegistry.getRegistry(ipAddress, port);
            rmiServerInterface = (RMIServerInterface) registry.lookup("RMIServerInterface");
            UnicastRemoteObject.exportObject(this, 0);

            //rmiServerInterface.sendMessage(, this);
            //rmiServerInterface.sendMessage("cane", this);
        }
        catch (RemoteException e) {}

        catch (NotBoundException e) {}
    }

    public String getId(){
        return clientController.getId();
    }

    @Override
    public void tryToLogIn() {
        try {
            rmiServerInterface.tryToLogIn(getId(),this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tryToJoinGame() {
        try {
            rmiServerInterface.tryToJoinGame(getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tryToCreateRoom(int numberOfPlayers) {
        try {
            rmiServerInterface.tryToCreateRoom(getId(), numberOfPlayers);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        try {
            System.out.println(familyMember);
            System.out.println(getId());
            rmiServerInterface.selectFamilyMember(familyMember,  getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void selectActionSpace(String actionSpaceId) {
        try {
            rmiServerInterface.selectActionSpace(actionSpaceId, getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dealWithVatican(int periodNumber) {
        System.out.println("What do you want to do? true: accept excommunication; false: reject excommunication");
        System.out.println("This is the excommunication penalty: " + getClientController().getView().getBoard().getTassels()[periodNumber-1]);

        Scanner scanner = new Scanner(System.in);
        return Boolean.parseBoolean(scanner.next());

    }


    public Gainable[] selectCouncilFavour(int numberOfFavours) {
        Gainable[] favours = selectFavours(numberOfFavours);
        return favours;
    }

    private Gainable[] selectFavours(int numberOfFavours){
        CouncilFavour councilFavour = new CouncilFavour(numberOfFavours);
        Scanner scanner = new Scanner(System.in);
        Gainable[] toReturn = new Gainable[numberOfFavours];

        System.out.println("Which favour do you want?");
        do {
            int i = 0;
            System.out.println(councilFavour);
            try {
                toReturn[i] = StaticVariables.COUNCIL_FAVOURS[scanner.nextInt()-1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not valid input given");
                continue;
            }
            numberOfFavours--;
            i++;
        } while (numberOfFavours > 0);

        return toReturn;
    }

    //@Override
    public ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        ActionSpace actionSpace = selectActionSpace(actionSpaces);
        return actionSpace;

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
            System.out.println(actionSpaces.get(i - 1));
            return actionSpaces.get(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            //return actionSpaces.get(actionSpaces.size()); //todo have user to write another number
            //todo prova
            return selectActionSpace(actionSpaces);

        }
    }


    @Override
    public void useSlaves(int quantity) {
        try {
            rmiServerInterface.useSlaves(quantity, getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveGame() {
        try {
            rmiServerInterface.leaveGame(getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendMessage2(String string) {
        System.out.println("chiamato metodo di client" + string);
    }

    @Override
    public void notifyRequestHandleOutcome(ResponseCode requestHandleOutcome ) {
        clientController.showOutcome(requestHandleOutcome);
    }

    @Override
    public void updateView(Board board, Collection< Player> players) {
        clientController.updateView(board, players);
    }


}
