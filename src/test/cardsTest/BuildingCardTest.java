package test.cardsTest;

import logic.cards.BuildingCard;
import logic.cards.Card;
import logic.cards.CardCost;
import logic.cards.CardType;
import logic.cards.cardEffects.CardEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesForCardTypeEffect;
import logic.gameStructure.PeriodNumber;
import logic.player.Player;
import logic.player.VictoryPoint;
import logic.resources.Money;
import logic.resources.SetOfResources;
import logic.resources.Stone;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class BuildingCardTest {
    @Test
    void getPermanentEffect() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));

        assertEquals(new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)).toString(), card.getPermanentEffect().toString());



    }

    @Test
    void setCardCost() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());
    }




    @Test
    void setActivationValue() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        card.setActivationValue(10);
        assertEquals(10, card.getActivationValue().intValue());
    }

    @Test
    void getActivationValue() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        assertEquals(5, card.getActivationValue().intValue());
    }

    @Test
    void getCardCost() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());
    }

    @Test
    void getCardType() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        assertEquals(CardType.BUILDING, card.getCardType());
    }

    @Test
    void getPeriod() {
        BuildingCard card = new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),
                5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND,
                new VictoryPoint(1)));
        assertEquals(PeriodNumber.FIRST, card.getPeriod());
    }

}