package network.client;

import logic.player.FamilyMember;
import network.ResponseCode;
import network.server.rmi.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

    @Override
    public void dealWithVatican() {

    }

    @Override
    public void selectCouncilFavour() {

    }

    @Override
    public void selectActionSpaceForExtraAction() {

    }

    @Override
    public void useSlaves() {

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
}
