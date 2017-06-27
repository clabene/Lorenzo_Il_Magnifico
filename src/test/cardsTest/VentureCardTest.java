package test.cardsTest;

import logic.cards.CardCost;
import logic.cards.CardType;
import logic.cards.VentureCard;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.PeriodNumber;
import logic.player.VictoryPoint;
import logic.pointsTracks.FaithPointsTrack;
import logic.resources.Money;
import logic.resources.SetOfResources;
import logic.resources.Stone;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 27/06/2017.
 */
class VentureCardTest {
    @Test
    void getPermanentEffect() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        assertEquals( new ReceiveGainablesEffect(new VictoryPoint(1)).toString(), card.getPermanentEffect().toString());

    }

    @Test
    void getImmediateEffect() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        assertEquals(new ReceiveGainablesEffect(new FaithPointsTrack(1)).toString(), card.getImmediateEffect().toString());
    }

    @Test
    void setCardCost() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());

    }


    @Test
    void setActivationValue() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));

        card.setActivationValue(10);
        assertEquals(10, card.getActivationValue().intValue());
    }



    @Test
    void getCardCost() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());


    }

    @Test
    void getCardType() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        assertEquals(CardType.VENTURE, card.getCardType());
    }

    @Test
    void getPeriod() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        assertEquals(PeriodNumber.FIRST, card.getPeriod());
    }

    @Test
    void getName() {
        VentureCard card = new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(),
                new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)),
                new ReceiveGainablesEffect(new VictoryPoint(1)));
        assertEquals("Sostegno al Vescovo", card.getName());
    }

}