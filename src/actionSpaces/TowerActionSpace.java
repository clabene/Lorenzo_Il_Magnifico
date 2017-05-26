package actionSpaces;

import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class TowerActionSpace extends ActionSpace {

    public TowerActionSpace(int minValueToPlaceFamiliar, Gainable ... gainables){
        super(1, ActionSpaceType.TOWER, minValueToPlaceFamiliar, gainables);

    }

    public boolean action(Player player){
        return true;
    }

}
