package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;


import java.util.ArrayList;
import java.util.Stack;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];
    private WinnerElector winnerElector = new WinnerElector();

    private Period period = new Period();
    private Turn turn = new Turn();
    private ActionPhase actionPhase = new ActionPhase();
    private FamilyMember selectedFamilyMember;
    private ActionSpace selectedActionSpace;

    public Game(){

    }

/*
    //todo add players

    public void startGame(){

        winnerElector.setPlayers(this.players); //game starts when all players joined

        Period firstPeriod = new Period(players);
        firstPeriod.startPeriod(3, tassels[0]);
        //todo change of turn order also between periods
        Period secondPeriod = new Period(players);
        secondPeriod.startPeriod(4, tassels[1]);

        Period thirdPeriod = new Period(players);
        thirdPeriod.startPeriod(5, tassels[2]);


        winnerElector.getWinner();
    }

*/


    public void addPlayer(Player player){
        this.players.add(player);
    }

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        //game.startGame();

        return;

    }

    public void setPeriod(Period period) {
        this.period = period;
    }
    public void setTurn(Turn turn) {
        this.turn = turn;
    }
    public void setActionPhase(ActionPhase actionPhase) {
        this.actionPhase = actionPhase;
    }


    public void selectionFamilyMember(Player player, ActionPhase actionPhase){
        this.selectedFamilyMember = actionPhase.selectionFamilyMemberPhase(player);
        if(selectedActionSpace != null) puttingFamilyMemberOnActionSpace(player);
    }
    public void selectionActionSpace(Player player, ActionPhase actionPhase){
        this.selectedActionSpace = actionPhase.selectionActionSpacePhase(player, turn.getBoard());
        if(selectedFamilyMember != null) puttingFamilyMemberOnActionSpace(player);
    }

    private void puttingFamilyMemberOnActionSpace(Player player){
        actionPhase.activateBonuses(player, selectedActionSpace);
        actionPhase.putFamilyMemberOnActionSpace(player, selectedFamilyMember, selectedActionSpace);
        selectedFamilyMember = null;
        selectedActionSpace = null;
    }

    public void checkingIfPlayable(Player player){
        actionPhase.checkPhasePlayable(player);
    }

    public void takingBackFamilyMembers(){
        turn.takeBackFamilyMembers(players);
    }

    public void puttingCardsOnBoard( Stack<Card> cards, Board board){
        turn.putCardsOnBoard(cards, board);
    }

    private  void gettingNextTurnOrder(Turn turn){
        this.players = turn.getNextTurnOrder(players);
    }

    private void settingPeriodDeck(){
        period.setPeriodDeck();
    }

    private void checkingExcomunication(int minFaithPoints, ExcommunicationTassel tassel){
        period.excommunicationCheck(players, minFaithPoints, tassel);
    }



}
