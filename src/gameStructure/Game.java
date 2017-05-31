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
        //todo change of turn order also between periods
        Period secondPeriod = new Period(players);

        Period thirdPeriod = new Period(players);

        //todo check points and say who is the winner



    }



}
