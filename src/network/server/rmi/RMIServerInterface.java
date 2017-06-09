package network.server.rmi;

import logic.player.FamilyMember;
import network.client.RMIClientInterface;
import network.server.RemotePlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface RMIServerInterface  extends Remote{

    void sendMessage(String string, RMIClientInterface c) throws RemoteException;



    public  void selectFamilyMember(FamilyMember familyMember, RemotePlayer player);

    /*
    * Client selects an area from the board
    * */
    public void selectBoardArea();

    /*
    * Client selects an action space from the board
    * */
    public void selectActionSpace();

    /*
    * Client decides if they want to support the Vatican or not.
    * This method is triggered by the server side.
    * */
    public  void dealWithVatican();
    /*
    * Client decides what bonus they will get form a council favour.
    * This method is triggered by the server side.
    * */
    public  void selectCouncilFavour();
    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public  void useSlaves();
}
