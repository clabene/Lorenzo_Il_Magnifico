package pointsTrack;

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
        super(StaticVariables.FAITH_TRACK_VICTORY_POINTS);
    }

    @Override
    public void gainedByPlayer(Player player){
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getFaithPoints().incrementTrackPosition();
    }

    @Override
    public void lostByPlayer(Player player){
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getFaithPoints().decrementTrackPosition();
    }



}
