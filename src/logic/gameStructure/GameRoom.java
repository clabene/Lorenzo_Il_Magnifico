package logic.gameStructure;

import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Player;
import network.server.RemotePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game = new Game();
    private Board board = new Board();
    private HashMap<String, RemotePlayer> players;


    public void addPlayerToRoom(RemotePlayer remotePlayer){
        remotePlayer.setGameRoom(this);
        remotePlayer.setBoard(board);
        players.put(remotePlayer.getId(), remotePlayer);
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkIfPlayerHasAvaialbeAction(Player player){
        return game.checkingIfPlayable(player);
    }

    public void selectFamilyMember(FamilyMember familyMember, String playerId){
        Boolean familyMemberCorrectlySelected = game.selectionFamilyMember(familyMember, players.get(playerId));
        if(familyMemberCorrectlySelected)
            players.get(playerId).notifyRequestHandleOutcome("OK");
        else players.get(playerId).notifyRequestHandleOutcome("NOT_OK");
    }

    //todo use this to handle turn switching
    private ArrayList<Player> getNextTurnOrder() {
        return game.gettingNextTurnOrder( (ArrayList) players.values(), board);
    }

}
