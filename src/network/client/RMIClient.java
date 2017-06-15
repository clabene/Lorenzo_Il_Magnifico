package network.client;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.utility.StaticVariables;
import network.ResponseCode;
import network.server.RemotePlayer;
import network.server.rmi.RMIServerInterface;

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
public class RMIClient extends AbstractNetworkClient implements RMIClientInterface{
    private String ipAddress;
    private int port;
    private RMIServerInterface rmiServerInterface;



    public RMIClient(ClientInterface clientController, int port){
        super(clientController);
        this.ipAddress = "127.0.0.1";
        this.port = port;
    }

//    @Override
    public void connect() {
        //connect to RMIServer
        try{
            Registry registry = LocateRegistry.getRegistry(ipAddress, port);
            rmiServerInterface = (RMIServerInterface) registry.lookup("RMIServerInterface");
            UnicastRemoteObject.exportObject(this, 0);
            rmiServerInterface.sendMessage("cane", this);
            System.out.println("sacco di breccia");
        }
        catch (RemoteException e) {}

        catch (NotBoundException e) {}
    }

    public String getId(){
        return super.getId();
    }

    @Override
    public void tryToLogIn() {
        try {
            rmiServerInterface.tryToLogIn(getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tryToJoinGame() {
        try {
            System.out.println("qui...." + getId());
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
            System.out.println(councilFavour);
            try {
                toReturn[toReturn.length] = StaticVariables.COUNCIL_FAVOURS[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not valid input given");
                numberOfFavours++;
            }
            numberOfFavours--;
        } while (numberOfFavours >= 0);

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
            return actionSpaces.get(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return actionSpaces.get(actionSpaces.size()); //todo have user to write another number
        }
    }


    @Override
    public void useSlaves(int quantity) {
        try {
            rmiServerInterface.useSlaves();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveGame() {

    }

    @Override
    public void sendMessage2(String string) {
        System.out.println("chiamato metodo di client" + string);
    }

    @Override
    public void notifyRequestHandleOutcome(ResponseCode requestHandleOutcome ) throws RemoteException{
        getClientController().showOutcome(requestHandleOutcome);
    }

    @Override
    public void updateView(Board board, Collection<Player> players) {
        getClientController().updateView(board, players);
    }


}
