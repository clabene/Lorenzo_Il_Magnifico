package logic.gameStructure;

import network.server.RemotePlayer;

import java.util.ArrayList;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game;
    private ArrayList<RemotePlayer> players;


    public GameRoom(Game game){
        this.game = new Game();
    }

    public void addPlayer(RemotePlayer remotePlayer){
        players.add(remotePlayer);
    }





}
