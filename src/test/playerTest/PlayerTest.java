package test.playerTest;

import logic.cards.*;
import logic.cards.cardEffects.ExchangeGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.exceptions.LimitedValueOffRangeException;
import logic.exceptions.NegativeResourceQuantityException;
import logic.gameStructure.PeriodNumber;
import logic.player.Player;
import logic.player.VictoryPoint;
import logic.pointsTracks.FaithPointsTrack;
import logic.pointsTracks.LandCardsPointsTrack;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.pointsTracks.PersonCardsPointsTrack;
import logic.resources.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class PlayerTest {
    @Test
    void gain() {
        Player player = new Player();
        player.gain( new SetOfResources(new Money(3),new Stone(3), new Wood(2)));
        assertEquals(2,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[3].getQuantity());

    }

    @Test
    void lose() {
        Player player = new Player();
        player.gain(new Money(3), new SetOfResources(new Stone(3), new Wood(2)));
        player.lose(new Money(1), new SetOfResources(new Stone(1), new Wood(1)));
        assertEquals(1,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(2,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(2,player.getPlank().getSetOfResources().getResources()[3].getQuantity());
        player.lose(new Money(4), new SetOfResources(new Stone(1), new Wood(1)));
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(1,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
        assertEquals(2,player.getPlank().getSetOfResources().getResources()[3].getQuantity());

    }



    @Test
    void tryToTakeCard() {
        Player player = new Player();
        BuildingCard card = new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1)));
        try {
            player.tryToTakeCard(card);
        } catch (LimitedValueOffRangeException e) {
            e.printStackTrace();
        }

        assertEquals(card, player.getPlank().getCards().getBuildingCards()[0]);
    }

    @Test
    void addResourcesToPlank() {
        Player player = new Player();
        player.addResourcesToPlank(new Money(3), new Stone(4));
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(4,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[3].getQuantity());

    }

    @Test
    void removeResourcesFromPlank() {
        Player player = new Player();
        player.addResourcesToPlank(new Money(3), new Stone(4));
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(4,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[3].getQuantity());
        try {
            player.removeResourcesFromPlank(new Money(2), new Stone(3));
        } catch (NegativeResourceQuantityException e) {
            e.printStackTrace();
        }
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(1,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(0,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
        assertEquals(1,player.getPlank().getSetOfResources().getResources()[3].getQuantity());
    }



    @Test
    void getBonuses() {




    }

    @Test
    void getId() {
        Player player = new Player(new Wood());
        assertEquals("Claudio", player.getId());
    }

    @Test
    void getPlank() {

    }

    @Test
    void getMilitaryPoints() {
        Player player = new Player();
        player.gain(new MilitaryPointsTrack(3));
        assertEquals(3, player.getMilitaryPoints().getTrackPosition().getValue());
    }

    @Test
    void getFaithPoints() {
        Player player = new Player();
        player.gain(new FaithPointsTrack(3));
        assertEquals(3, player.getFaithPoints().getTrackPosition().getValue());
    }

    @Test
    void getPersonCardsPoints() {
        Player player = new Player();
        player.gain(new PersonCardsPointsTrack(3));
        assertEquals(3, player.getPersonCardsPoints().getTrackPosition().getValue());
    }

    @Test
    void getLandCardsPoints() {
        Player player = new Player();
        player.gain(new LandCardsPointsTrack(3));
        assertEquals(3, player.getLandCardsPoints().getTrackPosition().getValue());
    }

    @Test
    void getPoints() {
        Player player = new Player();
        player.gain(new VictoryPoint(3));
        assertEquals(3, player.getPoints().getQuantity());

    }

    @Test
    void getFamilyMembersAvailable() {
        Player player = new Player();
        player.getFamilyMembers()[0].setInActionSpace(true);
        assertEquals(3, player.getFamilyMembersAvailable().toArray().length);
    }

    @Test
    void getFamilyMembers() {
        Player player = new Player();
        assertEquals(4, player.getFamilyMembers().length);

    }

    @Test
    void excommunicationDecision() {
    }

}