package cardEffects.bonuses;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;
import cardEffects.bonuses.Bonus;

/**
 * Created by IBM on 30/05/2017.
 */
public class MalusOnTowerBonuses implements Bonus {

    @Override
    public void activateBonus(ActionSpace actionSpace) {
        if(actionSpace.getActionSpaceType() == ActionSpaceType.TOWER)
            actionSpace.setBonus(null);
    }

}
