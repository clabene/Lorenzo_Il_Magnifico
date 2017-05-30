package cardEffects;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;

/**
 * Created by IBM on 30/05/2017.
 */
public class BonusOnFamilyMemberPlacement implements Bonus {

    private ActionSpaceType actionSpaceType;
    private int discount; //if this is set smaller than 0 this class describes a malus instead of a bonus

    public BonusOnFamilyMemberPlacement(ActionSpaceType actionSpaceType, int discount){
        this.actionSpaceType = actionSpaceType;
        this.discount = discount;
    }

    @Override
    public void activate(ActionSpace actionSpace) {
        if(actionSpace.getActionSpaceType() == this.actionSpaceType)
            actionSpace.decreaseMinValueRequested(discount);
    }


}
