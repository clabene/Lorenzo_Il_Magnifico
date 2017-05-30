package cardEffects;

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

    public void activate(Card card){
        if(card.getCardType() == this.cardType)
            try{
                card.getCardCost().getCost().resourcesSpent(discount);
            } catch (NegativeResourceQuantityException e){
                card.getCardCost().setCost(new SetOfResources());
            }
    }

}