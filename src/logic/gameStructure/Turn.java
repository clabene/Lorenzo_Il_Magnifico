package logic.gameStructure;


import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.board.Tower;
import logic.cards.Card;
import logic.player.FamilyMember;
import logic.player.Player;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Pinos on 25/05/2017.
 */
public class Turn {
    //private ArrayList<Player> players;
    //private Board board;

    public Turn(){
        //this.players = players;
        //this.board = new Board();
    }
/*
    public Board getBoard() {
        return board;
    }
*/
    /*
    cards are putted on board
    run action phase for each player and at the end of the turn get the
    order of the next turn from the council space.
     */

    /*
    public void playTurn(Stack<Card> cards){

        putCardsOnBoard(cards , board);

        for(Player tmp: players){
            ActionPhase actionPhase = new ActionPhase(tmp, board);
        }

        takeBackFamilyMembers();
        this.players = getNextTurnOrder();
    }

    public Board getBoard() {
        return board;
    }
    */

    /*
    Take the turn order from as an arrayList of Strings (id)
    and return the turn order as an arrayList of players
    */

    public ArrayList<Player> getNextTurnOrder(ArrayList<Player> players, Board board){

        ArrayList<Player> newTurn  = new ArrayList<>();
        ArrayList<String> ids = board.getCouncilArea().getTurnOrder();
        for(String id : ids)
            for(Player player: players)
                if (player.getId() == id)
                    newTurn.add(player);
        return newTurn;
    }

    /*
    Set the attribute to false of each family memeber for each Player such that it results not on a ActionSpace
    */


    public void takeBackFamilyMembers(ArrayList<Player> players){
        for(Player tmp: players){
            for(FamilyMember familyMember: tmp.getFamilyMembers()){
                familyMember.setInActionSpace(false);
            }
        }
    }

    /*
    input the deck of cards of the period from which the method will take the needed cards and put them on the board (input)
    the deck is setted such that is enough a double cycle to put cards on board
     */

    public void putCardsOnBoard(Stack<Card> cards, Board board){
        for(Tower tmp: board.getTowerArea().getTowers()){
            for(TowerActionSpace tmp1: tmp.getSpaces()){
                tmp1.setCard(cards.pop());
            }
        }
    }











}
