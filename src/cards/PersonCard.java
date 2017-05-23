package cards;


/**
 * Created by IBM on 14/05/2017.
 */
public class PersonCard extends Card {

    public PersonCard(PeriodNumber periodNumber, CardCost cardCost, CardEffect immediateCardEffect, CardEffect permanentCardEffect){
        super(CardType.PERSON, periodNumber);
        super.setActivationValue(null);
        super.setCardCost(cardCost);
        super.setImmediateEffect(immediateCardEffect);
        super.setPermanentEffect(permanentCardEffect);
    }

}
