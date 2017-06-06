package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.cards.VentureCard;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.player.VictoryPoint;

import java.util.ArrayList;
import java.util.Stack;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];
    private WinnerElector winnerElector = new WinnerElector();

    private Period period;
    private Turn turn;
    private ActionPhase actionPhase;



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





    public void addPlayer(Player player){
        this.players.add(player);
    }

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player("claudio");
        game.addPlayer(player);
        game.startGame();

        return;

    }

    public void selectionFamilyMember(ActionPhase actionPhase){
        actionPhase.selectionFamilyMemberPhase();
    }

    public void selectionActionSpace(ActionPhase actionPhase){
        actionPhase.selectionActionSpacePhase();
    }

    public void activationBonuses(ActionPhase actionPhase, ActionSpace actionSpace){
        actionPhase.activateBonuses(actionSpace);
    }

    public void movingFamilyMemberOnActionSpace(ActionPhase actionPhase, FamilyMember familyMember, ActionSpace actionSpace){
        actionPhase.putFamilyMemberOnActionSpace(familyMember, actionSpace);
    }

    public void takingBackFamilyMembers(Turn turn){
        turn.takeBackFamilyMembers();
    }

    public void puttingCardsOnBoard(Turn turn, Stack<Card> cards, Board board){
        turn.putCardsOnBoard(cards, board);
    }

    private  void gettingNextTurnOrder(Turn turn){
        turn.getNextTurnOrder();
    }

    private void settingPeriodDeck(Period period){
        period.setPeriodDeck();
    }

    private void checkingExcomunication(Period period, ArrayList<Player> players, int minFaithPoints, ExcommunicationTassel tassel){
        period.excommunicationCheck(players, minFaithPoints, tassel);
    }



}
