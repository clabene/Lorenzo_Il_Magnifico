package cards;

/**
 * Created by IBM on 14/05/2017.
 */
public class LandCard extends Card {

    public LandCard(String name, PeriodNumber periodNumber, int activationValue, CardEffect immediateCardEffect, CardEffect permanentCardEffect) {
        super(name, CardType.LAND, periodNumber);
        super.setActivationValue(activationValue);
        super.setCardCost(null);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }


}
