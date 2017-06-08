package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.SetOfResources;
import logic.utility.CardSetupHandler;


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
    private Stack<Card> deck = new Stack<>();

    private final SetOfResources initialResources = new SetOfResources(); //todo


/*
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

    //todo this has to return boolean. True: could add, false: could not add player to the game (i.e. game is full already)
    public void addPlayer(String id){
        players.add(new Player(id, initialResources.getResources()));
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


    public void selectionFamilyMember(FamilyMember familyMember, Player player){
        this.selectedFamilyMember = player.tryToSelectFamilyMember(familyMember);
        if(selectedActionSpace != null) puttingFamilyMemberOnActionSpace(player);
    }

    public void selectionActionSpace(String actionSpaceId, Player player, Board board){
        this.selectedActionSpace = board.tryToSelectActionSpace(actionSpaceId);
        if(selectedFamilyMember != null) puttingFamilyMemberOnActionSpace(player);
    }

    private void puttingFamilyMemberOnActionSpace(Player player) {
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

    public void puttingCardsOnBoard(Stack<Card> cards, Board board){
        turn.putCardsOnBoard(cards, board);
    }

    private  void gettingNextTurnOrder(Board board){
        this.players = turn.getNextTurnOrder(players, board);
    }

    private void settingPeriodDeck(){
        period.setPeriodDeck();
    }

    private void checkingExcomunication(int minFaithPoints, ExcommunicationTassel tassel){
        period.excommunicationCheck(players, minFaithPoints, tassel);
    }

    public void setDeck(){
        CardSetupHandler cartSetupHandler = new CardSetupHandler();
        this.deck = cartSetupHandler.readFromFile();
    }



}
