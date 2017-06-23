package network.server.rmi;

import logic.player.FamilyMember;
import network.client.rmi.RMIClientInterface;
import network.server.AbstractServer;
import network.server.ServerInterface;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIServer extends AbstractServer implements  RMIServerInterface,Serializable {

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
            //TODO: edo ha modificato... rebind
            reg.bind("RMIServerInterface", this);
            UnicastRemoteObject.exportObject(this, port);

        }catch (RemoteException e){
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
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
    public void useSlaves(int quantity,String playerId) {
        getServerController().getPlayer(playerId).useSlaves(quantity);
    }

    @Override
    public void tryToLogIn(String id, RMIClientInterface rmiClientInterface) throws RemoteException {

        RMIPlayer player = new RMIPlayer(rmiClientInterface,id);
        getServerController().tryToLogIn(id, player);
    }
/*
    @Override
    public void tryToLogIn(String playerid) {
        System.out.println("qui "+ playerid);

        getServerController().tryToLogIn(playerid, new RMIPlayer( ));
    }*/

    @Override
    public void tryToJoinGame(String playerid) {
        getServerController().tryToJoinGame(playerid);
    }

    @Override
    public void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS) {
        getServerController().tryToCreateRoom(id, NUMBER_OF_PLAYERS);
    }

    @Override
    public void leaveGame(String id) {
        getServerController().getPlayer(id).leaveGame();

    }


}
