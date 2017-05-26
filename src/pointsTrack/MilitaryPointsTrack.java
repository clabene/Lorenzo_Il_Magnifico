package pointsTrack;

import exceptions.NegativePointsException;
import interfaces.Losable;
import player.Player;
import utility.StaticVariables;

/**
 * Created by IBM on 20/05/2017.
 */
public class MilitaryPointsTrack extends PointsTrack implements Losable {

    private final int firstPlaceBonus = StaticVariables.FIRST_PLACE_MILITARY_BONUS;
    private final int secondPlaceBonus = StaticVariables.SECOND_PLACE_MILITARY_BONUS;

    public MilitaryPointsTrack(int trackPosition){
        super(trackPosition);
    }
    public MilitaryPointsTrack(){
        super(new int[StaticVariables.MILITARY_TRACK_POSITIONS]); //military position gives no extra victory points in general
    }


    @Override
    public void gainedByPlayer(Player player){
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getMilitaryPoints().incrementTrackPosition();
    }

    @Override
    public void lostByPlayer(Player player) throws NegativePointsException{
        if(player.getFaithPoints().getTrackPosition().getValue() < getTrackPosition().getValue()) throw new NegativePointsException();
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getMilitaryPoints().decrementTrackPosition();
    }

    @Override
    public String toString(){
        return "Military points: " + getTrackPosition().getValue();
    }


}
