package logic.bonuses;

import logic.actionSpaces.*;
import logic.cards.CardType;

/**
 * Created by IBM on 30/05/2017.
 */
public class BonusOnFamilyMemberPlacement implements Bonus {

    private ActionSpaceType actionSpaceType;
    private ActivationActionSpaceType activationActionSpaceType;
    private CardType cardType;
    private int bonus; //if this is set smaller than 0 this class describes a malus instead of a bonus

    public BonusOnFamilyMemberPlacement(ActionSpaceType actionSpaceType, int bonus){
        this.actionSpaceType = actionSpaceType;
        this.bonus = bonus;
    }
    public BonusOnFamilyMemberPlacement(ActivationActionSpaceType activationActionSpaceType, int bonus){
        this.actionSpaceType = ActionSpaceType.ACTIVATION;
        this.activationActionSpaceType = activationActionSpaceType;
        this.bonus = bonus;
    }
    public BonusOnFamilyMemberPlacement(CardType cardType, int bonus){
        this.cardType = cardType;
        this.bonus = bonus;
    }
    public BonusOnFamilyMemberPlacement(int bonus){
        this.bonus = bonus;
    }

    @Override
    public void activateBonus(ActionSpace actionSpace) {
        if(cardType == null && actionSpace.getActionSpaceType() == this.actionSpaceType)
            if(activationActionSpaceType == null || activationActionSpaceType == ((ActivationActionSpace)actionSpace).getActivationType())
                actionSpace.getLastFamilyMemberAdded().incrementFamilyMemberValue(bonus);

        if(actionSpaceType == null && ((TowerActionSpace)actionSpace).getCard().getCardType() == this.cardType)
            actionSpace.getLastFamilyMemberAdded().incrementFamilyMemberValue(bonus);

        if(cardType == null && actionSpaceType == null) //no matter what the action space is, family member always take the bonus
            actionSpace.getLastFamilyMemberAdded().incrementFamilyMemberValue(bonus);
    }


}
