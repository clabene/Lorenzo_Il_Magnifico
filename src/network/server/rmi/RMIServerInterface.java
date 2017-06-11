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

    void selectFamilyMember(FamilyMember familyMember, String playerId);

    /*
    * Client selects an area from the board
    * */
    void selectBoardArea();

    /*
    * Client selects an action space from the board
    * */
    void selectActionSpace(String actionSpaceId, String playerId);

    /*
    * Client decides if they want to support the Vatican or not.
    * This method is triggered by the server side.
    * */
    void dealWithVatican();
    /*
    * Client decides what bonus they will get form a council favour.
    * This method is triggered by the server side.
    * */
    void selectCouncilFavour();
    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    void useSlaves();

    void tryToLogIn(String id);

    /*
    * Client sends request to join a room
    * */
    void tryToJoinGame(String id) throws RemoteException;

    /*
    * Client sends request to start a game in a new room. Can specify the number of player that can join the game.
    * */
    void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS);

    /*
    * Client leaves the game
    * */
    void leaveGame(String id);
}
