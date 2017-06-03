package logic.bonuses;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.ActionSpaceType;

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
