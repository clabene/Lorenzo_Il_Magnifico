package actionSpaces;

import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class MarketActionSpace extends ActionSpace {

    public MarketActionSpace(Gainable ... gainables){
        super(1, ActionSpaceType.MARKET, 1, gainables);
    }

    public boolean action(Player player){

        player.gain( this.familiarAdded() );

        return true;
    }

}
