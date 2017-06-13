package network.client;

import logic.board.Board;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.utility.StaticVariables;
import network.ResponseCode;
import network.server.rmi.RMIServerInterface;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by IBM on 06/06/2017.
 */
public class RMIClient extends AbstractClient implements RMIClientInterface{
    private String ipAddress;
    private int port;
    private RMIServerInterface rmiServerInterface;



    public RMIClient(int port){
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
        }
        catch (RemoteException e) {}

        catch (NotBoundException e) {}
    }

    public String getId(){
        return super.getId();
    }

    @Override
    public void tryToLogIn() {
        rmiServerInterface.tryToLogIn(getId());
    }

    @Override
    public void tryToJoinGame() throws RemoteException {
        try {
            rmiServerInterface.tryToJoinGame(getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tryToCreateRoom(int numberOfPlayers) {
        rmiServerInterface.tryToCreateRoom(getId(), numberOfPlayers);
    }


    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        rmiServerInterface.selectFamilyMember(familyMember,  getId());
    }

    @Override
    public void selectBoardArea() {

    }

    @Override
    public void selectActionSpace(String actionSpaceId) {
        rmiServerInterface.selectActionSpace(actionSpaceId, getId());
    }


    public boolean dealWithVatican(ExcommunicationTassel tassel) {
        System.out.println("What do you want to do? true: accept excomunication; false: reject excomunication");
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

    @Override
    public void selectActionSpaceForExtraAction() {

    }

    @Override
    public void useSlaves() {
        rmiServerInterface.useSlaves();
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
