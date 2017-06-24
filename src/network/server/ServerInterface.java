package network.server;

import java.io.Serializable;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface ServerInterface extends Serializable{


    RemotePlayer getPlayer(String playerId);

    void tryToLogIn(String id, RemotePlayer player);

    void tryToJoinGame(String id);

    void tryToCreateRoom(String id, int NUMBER_OF_PLAYERS);


}
