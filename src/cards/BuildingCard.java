package cards;

import cards.cardEffects.CardEffect;

/**
 * Created by IBM on 14/05/2017.
 */
public class BuildingCard extends Card {

    public BuildingCard(String name, PeriodNumber periodNumber, CardCost cardCost, int activationValue, CardEffect immediateCardEffect, CardEffect permanentCardEffect){
        super(name, CardType.BUILDING, periodNumber);
        super.setActivationValue(activationValue);
        super.setCardCost(cardCost);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }


}
