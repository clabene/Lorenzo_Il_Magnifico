package cards;

/**
 * Created by IBM on 14/05/2017.
 */
public class BuildingCard extends Card {

    public BuildingCard(PeriodNumber periodNumber, CardCost cardCost, int activationValue, CardEffect immediateCardEffect, CardEffect permanentCardEffect){
        super(CardType.BUILDING, periodNumber);
        super.setActivationValue(null);
        super.setCardCost(cardCost);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }
    //ciao

}
