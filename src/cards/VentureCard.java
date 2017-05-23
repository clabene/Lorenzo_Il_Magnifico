package cards;

/**
 * Created by IBM on 14/05/2017.
 */
public class VentureCard extends Card {

    public VentureCard(PeriodNumber periodNumber, CardCost cardCost, CardCost cardCost2, CardEffect immediateCardEffect, CardEffect permanentCardEffect){
        super(CardType.VENTURE, periodNumber);
        super.setActivationValue(null);
        super.setCardCost(cardCost);
        super.setCardCost2(cardCost2);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }

}
