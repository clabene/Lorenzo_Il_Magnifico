package logic.cards;

import logic.cards.cardEffects.CardEffect;
import logic.gameStructure.PeriodNumber;

import java.io.Serializable;

/**
 * Created by IBM on 09/05/2017.
 */
public abstract class Card implements Serializable{

    /*
    * This is the most general card. All cards are described by a subset this class' attributes, but no card has all of them defined.
    * When this class is extended, subclasses should have their constructors to set a value for each of the attributes, eventually null.
    *
    * For example, no land card has a cost, so cards.LandCard class sets cardCost and cardCost2 to null,
    * while cards.VentureCard class uses setNoActivationValue in order to set activationValue to null, since no venture card has activation values.
    * On the other hand, cards.LandCard's constructors obliges to set a value for activationValue, since all land cards have an activation value.
    *
    * */


    private String name;
    private CardType cardType;
    private PeriodNumber period;
    private CardCost cardCost;
    private  CardEffect immediateEffect;
    private  CardEffect permanentEffect;

    private Integer activationValue;
    //did not use LimitedInteger here because this value is not necessarily the one of a dice. For example, some action spaces
    //have an activation value of 7

    public Card(String name, CardType cardType, PeriodNumber period) {
        this.name = name;
        this.cardType = cardType;
        this.period = period;
    }

    public CardEffect getPermanentEffect() {
        return permanentEffect;
    }

    public CardEffect getImmediateEffect() {
        return immediateEffect;
    }

    public void setCardCost(CardCost cardCost) {
        this.cardCost = cardCost;
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
    public Integer getActivationValue() {
        return activationValue;
    }

    public CardCost getCardCost() {
        return cardCost;
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

    public String getName(){
        return name;
    }

}