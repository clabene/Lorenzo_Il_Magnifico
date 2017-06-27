package test.actionSpacesTest;

import logic.actionSpaces.ActionSpaceType;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Color;
import logic.cards.BuildingCard;
import logic.cards.Card;
import logic.cards.CardCost;
import logic.cards.cardEffects.ExchangeGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.PeriodNumber;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.pointsTracks.FaithPointsTrack;
import logic.resources.Money;
import logic.resources.SetOfResources;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class TowerActionSpaceTest {
    @Test
    void action() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
    }

    @Test
    void setCard() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        Card card = new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1)));
        towerActionSpace.setCard(card);
        assertEquals(card, towerActionSpace.getCard());
    }

    @Test
    void getCard() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        Card card = new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1)));
        assertEquals(null , towerActionSpace.getCard());
        towerActionSpace.setCard(card);
        assertEquals(card, towerActionSpace.getCard());
    }

    @Test
    void familyMemberAdded() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        towerActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, towerActionSpace.getCovered());
    }

    @Test
    void familyMemberRemoved() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        towerActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, towerActionSpace.getCovered());
        towerActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, towerActionSpace.getCovered());
    }

    @Test
    void getLastFamilyMemberAdded() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        FamilyMember familyMember1 = new FamilyMember(Color.RED, 3);
        FamilyMember familyMember2 = new FamilyMember(Color.WHITE, 3);
        towerActionSpace.familyMemberAdded(familyMember);
        towerActionSpace.familyMemberAdded(familyMember1);
        towerActionSpace.familyMemberAdded(familyMember2);
        assertEquals(familyMember, towerActionSpace.getLastFamilyMemberAdded());
    }

    @Test
    void getActionSpaceType() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        assertEquals(ActionSpaceType.TOWER, towerActionSpace.getActionSpaceType());
    }

    @Test
    void decreaseMinValueRequested() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        assertEquals(1,towerActionSpace.getMinValueToPlaceFamiliar());
        towerActionSpace.decreaseMinValueRequested(1);
        assertEquals(0,towerActionSpace.getMinValueToPlaceFamiliar());
        towerActionSpace.decreaseMinValueRequested(1);
        assertEquals(-1,towerActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void getCovered() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        towerActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, towerActionSpace.getCovered());
        towerActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, towerActionSpace.getCovered());
    }

    @Test
    void getMinValueToPlaceFamiliar() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        assertEquals(1,towerActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void setBonus() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Money(3));
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        towerActionSpace.setBonus(bonus);
        assertEquals(bonus, towerActionSpace.getBonus());
    }

    @Test
    void getBonus() {
        TowerActionSpace towerActionSpace = new TowerActionSpace(1);
        assertEquals(new ArrayList<Gainable>(), towerActionSpace.getBonus());
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        towerActionSpace.setBonus(bonus);
        assertEquals(bonus, towerActionSpace.getBonus());
    }


}