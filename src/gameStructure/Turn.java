package gameStructure;


import actionSpaces.TowerActionSpace;
import board.Board;
import board.Tower;
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

    }


    /*
    cards are putted on board
    run action phase for each player and at the end of the turn get the
    order of the next turn from the council space.
     */


    public void playTurn(Stack<Card> cards){

        putCardsOnBoard(cards , board);

        for(Player tmp: players){
            ActionPhase actionPhase = new ActionPhase(tmp, board);
        }

        takeBackFamilyMembers();
        this.players = getNextTurnOrder();
    }

    /*
    Take the turn order from as an arrayList of Strings (id)
    and return the turn order as an arrayList of players
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

    /*
    Set the attribute to false of each family memeber for each Player such that it results not on a ActionSpace
    */


    public void takeBackFamilyMembers(){
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
