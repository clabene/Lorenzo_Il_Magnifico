package test.cardsTest;

import logic.cards.CardCost;
import logic.cards.CardType;
import logic.cards.LandCard;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.PeriodNumber;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.SetOfResources;
import logic.resources.Slave;
import logic.resources.Wood;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 27/06/2017.
 */
class LandCardTest {


    @Test
    void getPermanentEffect() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals(new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)).toString(), card.getPermanentEffect().toString());

    }

    @Test
    void getImmediateEffect() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals(new ReceiveGainablesEffect(new Slave()).toString(), card.getImmediateEffect().toString());
    }

    @Test
    void setCardCost() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());
    }


    @Test
    void setActivationValue() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        card.setActivationValue(10);
        assertEquals(10, card.getActivationValue().intValue());
    }

    @Test
    void getActivationValue() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals(3, card.getActivationValue().intValue());
    }

    @Test
    void getCardCost() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());
    }



    @Test
    void getCardType() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals(CardType.LAND, card.getCardType());
    }

    @Test
    void getPeriod() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals(PeriodNumber.SECOND, card.getPeriod());
    }

    @Test
    void getName() {
        LandCard card = new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3,
                new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2),
                new MilitaryPointsTrack(1)));
        assertEquals("Villaggio Montano", card.getName());
    }

}