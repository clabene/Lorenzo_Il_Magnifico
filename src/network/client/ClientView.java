package network.client;

import logic.board.Board;
import logic.player.Player;

import java.util.ArrayList;

/**
 * Created by IBM on 11/06/2017.
 */
public class ClientView {

    private ArrayList<Player> players = new ArrayList<>();

    private Board board = new Board();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
