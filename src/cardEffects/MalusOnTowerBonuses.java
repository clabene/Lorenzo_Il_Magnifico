package cardEffects;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;

/**
 * Created by IBM on 30/05/2017.
 */
public class MalusOnTowerBonuses implements Bonus {

    @Override
    public void activate(ActionSpace actionSpace) {
        if(actionSpace.getActionSpaceType() == ActionSpaceType.TOWER)
            actionSpace.setBonus(null);
    }

}
