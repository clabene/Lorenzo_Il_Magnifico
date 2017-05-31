package pointsTrack;

import exceptions.LimitedValueOffRangeException;
import interfaces.Gainable;
import utility.LimitedInteger;

/**
 * Created by IBM on 16/05/2017.
 */
public abstract class PointsTrack implements Gainable{

    /*
    * This class should be used to deploy points-tracks, like FaithPoints or MilitaryPoints. This class is to be written
    * so that it will encapsulate all the attributes of a single cell of the track.
    *
    * A few examples follow:
    * At the end of the game, a player will gain some victory points based on their position on the faith track. Using this
    * class to represent their faith points will make the calculations easier thanks to the victoryPointsOfPositions attribute.
    * Same when it comes to calculating those points coming from the number of person cards the player has at the end of the game:
    * player just needs to have a PersonCardsPointsTrack attribute of type pointsTrack, that updates going ahead every time they
    * get a person card.
    *
    * Note that victory points are better described by an int variable. This is because they are unlimited and because trackPosition
    * would also represent the amount of victory points the player gets at the end of the game, obviously, which would make the
    * calculations weird looking and over complicated.
    *
    * Note that military points are well described by such a model (even if they don't have victory points associated),
    * since it can encapsulate the bonuses attributes given at the end of the game and it allows the class to implement
    * Gainable and Losable and there is a limit number of military points a player can have. All this would be harder
    * to implement if military points were described by an int.
    *
    * The decision of extending this class to all possible pointsTrack's was made in order
    * to have a more readable code and no ugly victoryPointsOfPosition "exposed" in the constructor,
    * to make it easier to implement Gainable's method gainedByPlayer(Player player); , which is now implemented separately in
    * each subclass,
    * because only some of the subclasses have to implement Losable
    *
    * */

    private LimitedInteger trackPosition;
    private int[] victoryPointsOfPositions;


    public PointsTrack(int[] victoryPointsOfPositions) {
        initializeTrackPosition(victoryPointsOfPositions.length, 0);
        this.victoryPointsOfPositions = victoryPointsOfPositions; //this shall be loaded from a file or got from StaticVariables class
    }

    //this constructor is helpful because it allows to avoid giving Player a different parameter for each point they gain
    //when calling gain method
    public PointsTrack(int trackPosition){
        initializeTrackPosition(trackPosition, trackPosition);
    }

    private void initializeTrackPosition(int maxPosition, int initialPosition){
        try{
            this.trackPosition = new LimitedInteger(maxPosition, 0, initialPosition);
        } catch (LimitedValueOffRangeException e){
            e.printStackTrace();
        }
    }

    public void incrementTrackPosition(){
        try{
            trackPosition.increment();
        } catch (LimitedValueOffRangeException e){
            System.out.println("You cannot increase any more on this track");
        }
    }

    public void decrementTrackPosition(){
        try{
            trackPosition.decrement();
        } catch (LimitedValueOffRangeException e){
            System.out.println("You cannot decrease any more on this track");
        }
    }

   public int calculateVictoryPointsFromPosition(int trackPosition){
        return victoryPointsOfPositions[trackPosition];
    }


    public LimitedInteger getTrackPosition() {
        return trackPosition;
    }

}
