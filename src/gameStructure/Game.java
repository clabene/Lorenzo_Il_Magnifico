package gameStructure;

import cards.VentureCard;
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


        getWinner();



    }

    public void winnerCheck(){



        for(Player tmp: players){
            tmp.addVictoryPoints(tmp.getFaithPoints().calculateVictoryPointsFromPosition(tmp.getFaithPoints().getTrackPosition().getValue()));
            tmp.addVictoryPoints(tmp.getLandCardsPoints().calculateVictoryPointsFromPosition(tmp.getLandCardsPoints().getTrackPosition().getValue()));
            tmp.addVictoryPoints(tmp.getPersonCardsPoints().calculateVictoryPointsFromPosition(tmp.getPersonCardsPoints().getTrackPosition().getValue()));
            tmp.addVictoryPoints(tmp.getPlank().getSetOfResources().getQuantityOfResources()/5);
            for(VentureCard tmp1: (VentureCard[]) tmp.getPlank().getCards().getVentureCards()){
                tmp1.getPermanentEffect().activate(tmp);
            }
        }
        getVictoryPointsFromMilitaryPosition();

    }

    public int[] checkFirstPositionMilitaryTrack(){
        int[] positions = {-1, -1};

        for(Player tmp: players){
            if(positions[0] <= tmp.getMilitaryPoints().getTrackPosition().getValue()){
                positions[1] = positions[0];
                positions[0] = tmp.getMilitaryPoints().getTrackPosition().getValue();

            }else if(positions[1] <= tmp.getMilitaryPoints().getTrackPosition().getValue()){
                positions[1] = tmp.getMilitaryPoints().getTrackPosition().getValue();
            }

        }
        return positions;

    }

    public void getVictoryPointsFromMilitaryPosition(){
        int[] positions = checkFirstPositionMilitaryTrack();
        if(positions[1] == positions[0]){
            for(Player tmp: players){
                if(tmp.getMilitaryPoints().getTrackPosition().getValue() == positions[1])
                    tmp.addVictoryPoints(5);
            }
        }else{
            for(Player tmp: players){
                if(tmp.getMilitaryPoints().getTrackPosition().getValue() == positions[0]){
                    tmp.addVictoryPoints(5);
                }
                if(tmp.getMilitaryPoints().getTrackPosition().getValue() == positions[1]){
                    tmp.addVictoryPoints(2);
                }
            }
        }


    }

    public Player getWinner(){
        winnerCheck();
        Player winner = null;
        int winnerPoints = 0;
        for(Player tmp: players){
            if(winnerPoints < tmp.getVictoryPoints()){
                winner = tmp;
                winnerPoints = tmp.getVictoryPoints();
            }
        }
        return winner;
    }





}
