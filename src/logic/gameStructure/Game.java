package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.cards.VentureCard;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.player.VictoryPoint;

import java.util.ArrayList;


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
        firstPeriod.startPeriod(3, tessels[0]);
        //todo change of turn order also between periods
        Period secondPeriod = new Period(players);
        secondPeriod.startPeriod(4, tessels[1]);

        Period thirdPeriod = new Period(players);
        thirdPeriod.startPeriod(5, tessels[2]);


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

}
