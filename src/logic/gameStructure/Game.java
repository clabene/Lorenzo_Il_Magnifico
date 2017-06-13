package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.exceptions.ActionSpaceCoveredException;
import logic.exceptions.FamilyMemberSelectionException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;


import java.util.ArrayList;
import java.util.Stack;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Game {
    //private ArrayList<Player> players = new ArrayList<>();



    private Period period = new Period();
    private Turn turn = new Turn();
    private ActionPhase actionPhase = new ActionPhase();
    private FamilyMember selectedFamilyMember;
    private ActionSpace selectedActionSpace;


    private int turnNumber;
    private int periodNumber;

/*
    public void startGame(){

        winnerElector.setPlayers(this.players); //game starts when all players joined

        Period firstPeriod = new Period(players);
        firstPeriod.startPeriod(3, tassels[0]);
        //change of turn order also between periods
        Period secondPeriod = new Period(players);
        secondPeriod.startPeriod(4, tassels[1]);

        Period thirdPeriod = new Period(players);
        thirdPeriod.startPeriod(5, tassels[2]);

        winnerElector.getWinner();
    }

*/

/*
    // this has to return boolean. True: could add, false: could not add player to the game (i.e. game is full already)
    public void addPlayer(String id){
        players.add(new Player(id, initialResources.getResources()));
    }
*/

    public ActionSpace getSelectedActionSpace() {
        return selectedActionSpace;
    }
    public FamilyMember getSelectedFamilyMember() {
        return selectedFamilyMember;
    }

    public ResponseCode selectionFamilyMember(FamilyMember familyMember, Player player){
        try{
            this.selectedFamilyMember = player.tryToSelectFamilyMember(familyMember);
        } catch (FamilyMemberSelectionException e){
            return ResponseCode.NOT_OK;
        }
        return ResponseCode.OK;
        //if(selectedFamilyMember == null) return false;
        //if(selectedActionSpace != null) puttingFamilyMemberOnActionSpace(player);
        //return true;
    }

    public ResponseCode selectionActionSpace(String actionSpaceId, Player player, Board board){
        try{
            this.selectedActionSpace = board.tryToSelectActionSpace(actionSpaceId);
        } catch (ActionSpaceCoveredException e){
            return ResponseCode.NOT_OK;
        }
        return ResponseCode.OK;
        //if(selectedActionSpace == null) return false;
        //if(selectedFamilyMember != null) puttingFamilyMemberOnActionSpace(player);
        //return true;
    }

    public ResponseCode puttingFamilyMemberOnActionSpace(Player player) {
        actionPhase.activateBonuses(player, selectedActionSpace);
        Boolean b = actionPhase.putFamilyMemberOnActionSpace(player, selectedFamilyMember, selectedActionSpace);
        selectedFamilyMember = null;
        selectedActionSpace = null;
        if(b) return ResponseCode.OK;
        return ResponseCode.NOT_OK;
    }

    public ResponseCode playingExtraAction(Player player, int familyMemberValue, ActionSpace actionSpace){

        actionPhase.activateBonuses(player, actionSpace);

        Boolean b = actionPhase.putFamilyMemberOnActionSpace(player,
                new FamilyMember(null, familyMemberValue, player.getId()),
                actionSpace);

        if(b) return ResponseCode.OK;
        return ResponseCode.NOT_OK;
    }

    public boolean checkingIfPlayable(Player player){
        return actionPhase.checkPhasePlayable(player);
    }

    public void takingBackFamilyMembers(Player player, Board board){
        turn.takeBackFamilyMembers(player, board);
    }
/*  da cancellare //todo
    public void puttingCardsOnBoard(Stack<Card> cards, Board board){
        turn.putCardsOnBoard(cards, board);
    }
*/
    public void gettingNextTurnOrder(ArrayList<String> players, Board board){
        turn.getNextTurnOrder(players, board);
        return;
    }

    public ResponseCode useSlaves(Player player, int quantity){
        if(selectedFamilyMember == null) return ResponseCode.NOT_OK;

        if(actionPhase.incrementFamilyMemberValueRequest(player, selectedFamilyMember, quantity))
            return ResponseCode.OK;
        return ResponseCode.NOT_OK;
    }

    public boolean hasEnoughFaithPoints(Player player, int minFaithPoints){
        return player.getFaithPoints().getTrackPosition().getValue() >= minFaithPoints;
    }

    public void takeExcommunication(Player player, ExcommunicationTassel tassel, boolean notSupporting){
        if(!notSupporting){
            player.gain(player.getFaithPoints().calculateVictoryPointsFromPosition());
            player.lose(player.getFaithPoints());
            return;
        }
        tassel.activate(player);
    }

    public void changeTurn(){

        if(turnNumber == 2){
            periodNumber++;
            period = new Period();
            turn = new Turn();
            turnNumber = 1;

        }
        else{
            turnNumber++;
            turn = new Turn();

        }
    }

    public int getCurrentPeriodNumber() {
        return periodNumber;
    }

    public void throwDice(Board board){
        board.setDice();
    }

    public Turn getTurn() {
        return turn;
    }
}
