package cards;

import cards.cardEffects.CardEffect;
import gameStructure.PeriodNumber;

/**
 * Created by IBM on 14/05/2017.
 */
public class VentureCard extends Card {

    public VentureCard(String name, PeriodNumber periodNumber, CardCost cardCost, CardEffect immediateCardEffect, CardEffect permanentCardEffect){
        super(name, CardType.VENTURE, periodNumber);
        super.setActivationValue(null);
        super.setCardCost(cardCost);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }

}
