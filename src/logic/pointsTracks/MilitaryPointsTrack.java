package logic.pointsTracks;

import logic.exceptions.NegativePointsException;
import logic.interfaces.Losable;
import logic.player.Player;
import logic.utility.StaticVariables;

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

    /*
    * If player has to lose more of the points they actually have, they will lose all their points and eventually throw
    * a negative points exception.
    * */
    @Override
    public void lostByPlayer(Player player) throws NegativePointsException{
        if(player.getMilitaryPoints().getTrackPosition().getValue() < getTrackPosition().getValue()) {
            for(int i = player.getMilitaryPoints().getTrackPosition().getValue(); i>0; i--)
                player.getMilitaryPoints().decrementTrackPosition();
            throw new NegativePointsException();
        }
        else
            for(int i = getTrackPosition().getValue(); i>0; i--)
                player.getMilitaryPoints().decrementTrackPosition();
    }

    @Override
    public String toString(){
        return "MP: " + getTrackPosition().getValue();
    }


}
