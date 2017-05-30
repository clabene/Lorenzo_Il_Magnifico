package cardEffects;

import actionSpaces.ActionSpace;
import actionSpaces.ActionSpaceType;
import actionSpaces.TowerActionSpace;
import cards.Card;
import cards.CardType;
import exceptions.NegativeResourceQuantityException;
import resources.Resource;
import resources.SetOfResources;

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
        if(card.getCardType() == this.cardType)
            try{
                card.getCardCost().getCost().resourcesSpent(discount);
            } catch (NegativeResourceQuantityException e){
                card.getCardCost().setCost(new SetOfResources());
            }
    }

    @Override
    public void activate(ActionSpace actionSpace){
        if(actionSpace.getActionSpaceType() == ActionSpaceType.TOWER)
            decrementCardCost( ((TowerActionSpace) actionSpace).getCard() );
    }

}