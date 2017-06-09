package network.server;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface ServerInterface {
    public RemotePlayer getPlayer(String id);

    RemotePlayer getPlayer(String id);

    void tryToLogIn(String id, RemotePlayer player);

    /*
    * Client sends request to join a room
    * */
    void tryToJoinGame(String id);

    /*
    * Client sends request to start a game in a new room. Can specify the number of player that can join the game.
    * */
    void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS);

    /*
    * Client leaves the game
    * */
    void leaveGame(String id);
}
