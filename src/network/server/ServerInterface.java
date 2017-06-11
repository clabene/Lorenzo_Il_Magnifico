package network.server;

import java.rmi.RemoteException;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface ServerInterface {
    public RemotePlayer getPlayer(String id);



    void tryToLogIn(String id, RemotePlayer player);

    /*
    * Client sends request to join a room
    * */
    void tryToJoinGame(String id) throws RemoteException;

    /*
    * Client sends request to start a game in a new room. Can specify the number of player that can join the game.
    * */
    void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS) throws RemoteException;

    /*
    * Client leaves the game
    * */
    void leaveGame(String id);
}
