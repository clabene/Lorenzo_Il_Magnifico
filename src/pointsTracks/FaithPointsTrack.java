package pointsTracks;

import exceptions.NegativePointsException;
import interfaces.Losable;
import player.Player;
import utility.StaticVariables;

/**
 * Created by IBM on 20/05/2017.
 */
public class FaithPointsTrack extends PointsTrack implements Losable {

    public FaithPointsTrack(int trackPosition){
        super(trackPosition);
    }

    public FaithPointsTrack(){
        super(StaticVariables.NUMBER_OF_FAITH_TRACK_VICTORY_POINTS);
    }

    @Override
    public void gainedByPlayer(Player player) {
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getFaithPoints().incrementTrackPosition();
    }

    /*
    * If player has to lose more of the points they actually have, they will lose all their points and eventually throw
    * a negative points exception.
    * */
    @Override
    public void lostByPlayer(Player player) throws NegativePointsException {
        if(player.getFaithPoints().getTrackPosition().getValue() < getTrackPosition().getValue()){
            for(int i = player.getFaithPoints().getTrackPosition().getValue(); i>0; i--)
                player.getFaithPoints().decrementTrackPosition();
            throw new NegativePointsException();
        }

        else
            for(int i = getTrackPosition().getValue(); i>0; i--)
                player.getFaithPoints().decrementTrackPosition();
    }

    @Override
    public String toString(){
        return "Faith points: " + getTrackPosition().getValue();
    }


}
