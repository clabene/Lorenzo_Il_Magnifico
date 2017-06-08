package logic.gameStructure;

import logic.player.FamilyMember;
import logic.player.Player;
import network.server.RemotePlayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game;
    private HashMap<String, RemotePlayer> players;

    public GameRoom(Game game){
        this.game = new Game();
    }


    public void addPlayerToRoom(RemotePlayer remotePlayer){
        players.put(remotePlayer.getId(), remotePlayer);
    }

    public void addPlayerToGame(String id){
        game.addPlayer(id);
    }


    public void selectFamilyMember(FamilyMember familyMember, String id){
        game.selectionFamilyMember(familyMember, players.get(id));
    }



}
