package gameStructure;


import board.Board;
import cards.Card;
import player.FamilyMember;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Pinos on 25/05/2017.
 */
public class Turn {
    ArrayList<Player> players;
    Board board;

    public Turn(ArrayList<Player> players){
        this.players = players;
        this.board = new Board();
        //todo think about how to put the cards in the tower space
    }


    /*  run action phase for each player and at the end of the turn get the
     *  order of the next turn from the council space.
     *
     *
     *
     *
     */


    public void playTurn(Stack<Card> cards){

        Collections.shuffle(cards);

        //todo to give the cards


        for(Player tmp: players){
            ActionPhase actionPhase = new ActionPhase(tmp, board);
        }

        //todo to take the cards given in the turn that are not taken by the players

        takeBackFamilyMembers();


        this.players = getNextTurnOrder();
    }

    /*  Take the turn order from as an arrayList of Strings (id)
     *  and return the turn order as an arrayList of players
     *
     */

    public ArrayList<Player> getNextTurnOrder(){

        ArrayList<Player> newTurn  = new ArrayList<>();
        ArrayList<String> ids = board.getCouncilArea().getTurnOrder();
        for(String id : ids){
            for(Player player: players){
                if(player.getId() == id)
                    newTurn.add(player);
            }
        }

        return newTurn;

    }

    /* Set the attribute to false of each family memeber for each Player such that it results not on a ActionSpace
     *
     *
     */


    public void takeBackFamilyMembers(){
        for(Player tmp: players){
            for(FamilyMember familyMember: tmp.getFamilyMembers()){
                familyMember.setInActionSpace(false);
            }
        }
    }











}
