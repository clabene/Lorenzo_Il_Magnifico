package cards.cardEffects.bonuses;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;
import actionSpaces.TowerActionSpace;
import cards.Card;
import cards.CardType;
import exceptions.NegativeResourceQuantityException;
import resources.SetOfResources;
import resources.Resource;

/**
 * Created by IBM on 30/05/2017.
 */
public class BonusOnCardTake implements Bonus {

    private CardType cardType;
    private Resource discount;

    public BonusOnCardTake(CardType cardType, Resource discount){
        this.cardType = cardType;
        this.discount = discount;
    }

    public void decrementCardCost(Card card){
        if(card.getCardType() == this.cardType && card.getCardCost().getCost() instanceof SetOfResources  )
            try{
                card.getCardCost().getCost().resourcesSpent(discount);
            } catch (NegativeResourceQuantityException e){
                card.getCardCost().setCost(new SetOfResources());
            }
    }

    @Override
    public void activateBonus(ActionSpace actionSpace){
        if(actionSpace.getActionSpaceType() == ActionSpaceType.TOWER)
            decrementCardCost( ((TowerActionSpace) actionSpace).getCard() );
    }

}