package network.server.rmi;

import logic.player.FamilyMember;
import network.client.RMIClientInterface;
import network.server.AbstractServer;
import network.server.RemotePlayer;
import network.server.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIServer extends AbstractServer implements RMIServerInterface {

    public RMIServer(ServerInterface serverController){
        super(serverController);

    }

    @Override
    public void startServer(int port) {
        System.out.println("RMI Server started");


        try{

            Registry reg;
            try{
                reg = LocateRegistry.createRegistry(port);
                System.out.println("java RMI registry created");
            }catch (Exception e){
                System.out.println("Using existing registry");
                reg = LocateRegistry.getRegistry();
            }

            reg.rebind("RMIServerInterface", this);
            UnicastRemoteObject.exportObject(this, port);
            System.out.println("ooooooooooooooooooooooo");



        }catch (RemoteException e){
            e.printStackTrace();
        }


    }

    @Override
    public void sendMessage(String string, RMIClientInterface c) throws RemoteException {
        System.out.println("chiamato metodo di server" +string);
        c.sendMessage2(string);
    }


    @Override
    public void selectFamilyMember(FamilyMember familyMember, String playerId) {
         getServerController().getPlayer(playerId).selectFamilyMember(familyMember);
    }

    @Override
    public void selectBoardArea() {

    }


    @Override
    public void selectActionSpace(String actionSpaceId, String playerId) {
        getServerController().getPlayer(playerId).selectActionSpace(actionSpaceId);

    }

    @Override
    public void dealWithVatican() {


    }

    @Override
    public void selectCouncilFavour() {

    }

    @Override
    public void useSlaves() {

    }

    @Override
    public void tryToLogIn(String playerid) {
        RMIPlayer player = new RMIPlayer();
        getServerController().tryToLogIn(playerid, player);
    }

    @Override
    public void tryToJoinGame(String playerid){

        try {
            getServerController().tryToJoinGame(playerid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS) {
        try {
            getServerController().tryToCreateRoom(id, NUMBER_OF_PLAYERS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void leaveGame(String id) {

    }


}
