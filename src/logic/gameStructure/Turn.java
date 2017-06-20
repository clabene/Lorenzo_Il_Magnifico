package logic.gameStructure;


import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.player.FamilyMember;
import logic.player.Player;

import javax.print.attribute.standard.PrinterLocation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Pinos on 25/05/2017.
 */
public class Turn implements Serializable{
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

    public void getNextTurnOrder(ArrayList<String> players, Board board){
        players = board.getTurnOrder();
    }

    /*
    Set the attribute to false of each family memeber for each Player such that it results not on a ActionSpace
    */


    public void takeBackFamilyMembers(Player player, Board board){
        for(FamilyMember familyMember: player.getFamilyMembers())
                familyMember.setInActionSpace(false);

        for(ActionSpace tmp : board.getHashMap().values()){
            tmp.resetActionSpace();
            tmp.setCovered(false);
        }
    }


    /*
    input the deck of cards of the period from which the method will take the needed cards and put them on the board (input)
    the deck is setted such that is enough a double cycle to put cards on board
     */


/* da cancellare (check)
    public void putCardsOnBoard(Stack<Card> cards, Board board){
        int i = 1;
        for(ActionSpace tmp : board.getHashMap().values()){
            ((TowerActionSpace) tmp).setCard(cards.pop());
            i++;
            if(i == 16)break;
        }
    }
*/

}
