package gameStructure;

import player.Player;

import java.util.ArrayList;

/**
 * Created by Pinos on 25/05/2017.
 */
public class Game {
    ArrayList<Player> players = new ArrayList<>();

    //todo add players

    public void startGame(){

        Period firstPeriod = new Period(players);
        firstPeriod.startPeriod(3);
        //todo change of turn order also between periods
        Period secondPeriod = new Period(players);
        secondPeriod.startPeriod(4);

        Period thirdPeriod = new Period(players);
        thirdPeriod.startPeriod(5);

        //todo check points and say who is the winner



    }



}
