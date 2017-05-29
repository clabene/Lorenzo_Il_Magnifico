package gameStructure;


import board.Board;
import player.Player;

import java.util.ArrayList;

/**
 * Created by Pinos on 25/05/2017.
 */
public class Turn {
    ArrayList<Player> players;
    Board board;

    public Turn(ArrayList<Player> players, Board board){
        this.players = players;
        this.board = board;
    }

    public void playTurn(){
        for(Player tmp: players){
            ActionPhase actionPhase = new ActionPhase(tmp, board);
        }
    }

    public void setNextTurnOrder(){
        //this.players =  board.getCouncilArea().getTurnOrder();
        ArrayList<Player> newTurn  = new ArrayList<>();
        //todo
    }







}
