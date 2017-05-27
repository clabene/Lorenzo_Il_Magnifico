package cards;

import cardEffects.CardEffect;

/**
 * Created by IBM on 09/05/2017.
 */
public abstract class Card {

    /*
    * This is the most general card. All cards are described by a subset this class' attributes, but no card has all of them defined.
    * When this class is extended, subclasses should have their constructors to set a value for each of the attributes, eventually null.
    *
    * For example, no land card has a cost, so cards.LandCard class sets cardCost and cardCost2 to null,
    * while cards.VentureCard class uses setNoActivationValue in order to set activationValue to null, since no venture card has activation values.
    * On the other hand, cards.LandCard's constructors obliges to set a value for activationValue, since all land cards have an activation value.
    *
    * cost2 is null if not differently specified since only 2 cards have two costs.
    * */


    private String name;
    private CardType cardType; //might wanna delete this attribute
    private PeriodNumber period;
    private CardCost cardCost;
    private CardCost cardCost2 = null;
    private CardEffect immediateEffect;
    private CardEffect permanentEffect;

    private Integer activationValue;
    //did not use LimitedInteger here because this value is not necessarily the one of a dice. For example, some action spaces
    //have an activation value of 7

    public Card(String name, CardType cardType, PeriodNumber period) {
        this.name = name;
        this.cardType = cardType;
        this.period = period;
    }

    public void setCardCost(CardCost cardCost) {
        this.cardCost = cardCost;
    }

    public void setCardCost2(CardCost cardCost2) {
        this.cardCost2 = cardCost2;
    }

    public void setImmediateEffect(CardEffect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public void setPermanentEffect(CardEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public void setActivationValue(Integer activationValue) {
        this.activationValue= activationValue;
    }



    public CardType getCardType() {
        return cardType;
    }
    public PeriodNumber getPeriod() {
        return period;
    }

    @Override
    public String toString(){
        return name+" ("+cardType.toString()+" card, "+period.toString()+" period)";
    }

}