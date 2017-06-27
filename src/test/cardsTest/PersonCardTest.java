package test.cardsTest;

import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.ActivationActionSpaceType;
import logic.cards.CardCost;
import logic.cards.CardType;
import logic.cards.PersonCard;
import logic.cards.cardEffects.PlayExtraActionPhaseEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.cards.cardEffects.SetOfCardEffects;
import logic.gameStructure.PeriodNumber;
import logic.pointsTracks.FaithPointsTrack;
import logic.resources.Money;
import logic.resources.SetOfResources;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 27/06/2017.
 */
class PersonCardTest {
    @Test
    void getPermanentEffect() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);

        assertEquals(null, card.getPermanentEffect());

    }

    @Test
    void getImmediateEffect() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        assertEquals(new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                ActivationActionSpaceType.PRODUCTION))).toString(), card.getImmediateEffect().toString());
    }

    @Test
    void setCardCost() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());

    }



    @Test
    void setActivationValue() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        card.setActivationValue(10);
        assertEquals(10, card.getActivationValue().intValue());
    }



    @Test
    void getCardCost() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        card.setCardCost(new CardCost(new SetOfResources(new Wood(2))));
        assertEquals(new CardCost(new SetOfResources(new Wood(2))).toString(), card.getCardCost().toString());

    }

    @Test
    void getCardType() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        assertEquals(CardType.PERSON, card.getCardType());
    }

    @Test
    void getPeriod() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        assertEquals(PeriodNumber.THIRD, card.getPeriod());
    }

    @Test
    void getName() {
        PersonCard card = new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))),
                new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1,
                        ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null);
        assertEquals("Vescovo", card.getName());
    }

}