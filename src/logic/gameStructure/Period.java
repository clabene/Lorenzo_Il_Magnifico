package logic.gameStructure;

import logic.cards.*;
import com.google.gson.Gson;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Period {

    //private ArrayList<Player> players;
    private Stack<Card> cards;
    private Stack<Card> deck;

/*
    public Period( ArrayList<Player> players){

        this.players = players;
    }

    public void startPeriod(int minFaithPoints, ExcommunicationTassel tessel ){
        Stack<Card> PeriodDeck = setPeriodDeck();
        Turn firstTurn= new Turn(players);
        firstTurn.playTurn(PeriodDeck);

        Turn secondTurn = new Turn(players);
        secondTurn.playTurn(PeriodDeck);

        excommunicationCheck(players, minFaithPoints, tessel);

        //todo church  operations


    }
    */




















    public void excommunicationCheck(ArrayList<Player> players, int minFaithPoints, ExcommunicationTassel tassel){


        for(Player tmp: players){
            if(tmp.getFaithPoints().getTrackPosition().getValue() < minFaithPoints){
                System.out.println("You faith points are not enough, so you receive an excommunication from the Church");
                tassel.activate(tmp);
            }
            else{
                tmp.excommunicationDecision();
            }
        }

    }














}
