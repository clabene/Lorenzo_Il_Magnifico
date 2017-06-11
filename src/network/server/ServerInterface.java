package network.server;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface ServerInterface {


    RemotePlayer getPlayer(String playerId);

    void tryToLogIn(String id, RemotePlayer player);

    void tryToJoinGame(String id);

    void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS);

    void leaveGame(String id);
}
