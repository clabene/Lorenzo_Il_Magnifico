package pointsTrack;

import player.Player;
import utility.StaticVariables;

/**
 * Created by IBM on 20/05/2017.
 */
public class LandCardsPointsTrack extends PointsTrack{

    //might wanna override decrementPosition so that it does nothing. Indeed, no player can lose cards.

    public LandCardsPointsTrack(int trackPosition){
        super(trackPosition);
    }

    public LandCardsPointsTrack(){
        super(StaticVariables.NUMBER_OF_LAND_CARDS_VICTORY_POINTS);
    }


    @Override
    public void gainedByPlayer(Player player){
        for(int i = getTrackPosition().getValue(); i>0; i--)
            player.getLandCardsPoints().incrementTrackPosition();
    }


}
