package test.playerTest;

import logic.cards.BuildingCard;
import logic.cards.CardCost;
import logic.cards.cardEffects.ExchangeGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.exceptions.LimitedValueOffRangeException;
import logic.gameStructure.PeriodNumber;
import logic.player.Plank;
import logic.pointsTracks.FaithPointsTrack;
import logic.resources.Money;
import logic.resources.SetOfResources;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class PlankTest {
    @Test
    void getCards() {
        Plank plank = new Plank();
        BuildingCard card = new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1)));

        try {
            plank.getCards().cardAdded(card);
        } catch (LimitedValueOffRangeException e) {
            e.printStackTrace();
        }
        assertEquals(card, plank.getCards().getBuildingCards()[0]);


    }



}