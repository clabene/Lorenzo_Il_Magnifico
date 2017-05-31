package cardEffects.bonuses;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;
import actionSpaces.TowerActionSpace;
import cardEffects.bonuses.Bonus;
import cards.CardType;

/**
 * Created by IBM on 30/05/2017.
 */
public class BonusOnFamilyMemberPlacement implements Bonus {

    private ActionSpaceType actionSpaceType;
    private CardType cardType;
    private int discount; //if this is set smaller than 0 this class describes a malus instead of a bonus

    public BonusOnFamilyMemberPlacement(ActionSpaceType actionSpaceType, int discount){
        this.actionSpaceType = actionSpaceType;
        this.discount = discount;
    }

    public BonusOnFamilyMemberPlacement(CardType cardType, int discount){
        this.cardType = cardType;
        this.discount = discount;
    }

    @Override
    public void activateBonus(ActionSpace actionSpace) {
        if(cardType == null && actionSpace.getActionSpaceType() == this.actionSpaceType)
            actionSpace.decreaseMinValueRequested(discount);
        if(actionSpaceType == null && ((TowerActionSpace)actionSpace).getCard().getCardType() == this.cardType )
            actionSpace.decreaseMinValueRequested(discount);
    }


}
