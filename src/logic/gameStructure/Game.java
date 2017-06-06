package logic.gameStructure;

import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.Player;

import java.util.ArrayList;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Game {
    ArrayList<Player> players = new ArrayList<>();
    ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];
    WinnerElector winnerElector = new WinnerElector();

    public Game(){

    }


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





}
