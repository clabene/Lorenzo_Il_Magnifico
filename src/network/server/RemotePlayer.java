package network.server;


import logic.gameStructure.GameRoom;
import logic.player.Player;

/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class RemotePlayer extends Player{

    private GameRoom gameRoom;


    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

}
