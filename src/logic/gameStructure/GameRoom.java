package logic.gameStructure;

import logic.player.Player;

import java.util.ArrayList;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game;
    private ArrayList<Player> players;


    public GameRoom(Game game){
        this.game = new Game();
        this.players = game.getPlayers();
    }




}
