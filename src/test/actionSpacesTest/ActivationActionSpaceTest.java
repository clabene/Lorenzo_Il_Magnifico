package test.actionSpacesTest;

import logic.actionSpaces.ActionSpaceType;
import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.ActivationActionSpaceType;
import logic.board.Color;
import logic.cards.BuildingCard;
import logic.cards.Card;
import logic.cards.CardCost;
import logic.cards.cardEffects.ExchangeGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.exceptions.LimitedValueOffRangeException;
import logic.gameStructure.PeriodNumber;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
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
class ActivationActionSpaceTest {

    @Test
    void getActivationType() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        assertEquals(ActivationActionSpaceType.HARVEST , activationActionSpace.getActivationType());
        ActivationActionSpace activationActionSpace1 = new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION);
        assertEquals(ActivationActionSpaceType.PRODUCTION , activationActionSpace1.getActivationType());
    }

    @Test
    void action() throws LimitedValueOffRangeException {
        Player player = new Player();
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION);
        Card card = new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1)));
        try {
            player.tryToTakeCard(card);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (LimitedValueOffRangeException e) {
            e.printStackTrace();
        }
        boolean action = activationActionSpace.action(player);
        assertTrue(action);
    }

    @Test
    void familyMemberAdded() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        activationActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, activationActionSpace.getCovered());
    }

    @Test
    void familyMemberRemoved() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        activationActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, activationActionSpace.getCovered());
        activationActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, activationActionSpace.getCovered());

    }

    @Test
    void getLastFamilyMemberAdded() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(3, ActivationActionSpaceType.HARVEST);
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        FamilyMember familyMember1 = new FamilyMember(Color.RED, 3);
        FamilyMember familyMember2 = new FamilyMember(Color.WHITE, 3);
        activationActionSpace.familyMemberAdded(familyMember);
        activationActionSpace.familyMemberAdded(familyMember1);
        activationActionSpace.familyMemberAdded(familyMember2);
        assertEquals(familyMember2, activationActionSpace.getLastFamilyMemberAdded());

    }

    @Test
    void getActionSpaceType() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        assertEquals(ActionSpaceType.ACTIVATION, activationActionSpace.getActionSpaceType());

    }

    @Test
    void decreaseMinValueRequested() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        assertEquals(1,activationActionSpace.getMinValueToPlaceFamiliar());
        activationActionSpace.decreaseMinValueRequested(1);
        assertEquals(0,activationActionSpace.getMinValueToPlaceFamiliar());
        activationActionSpace.decreaseMinValueRequested(1);
        assertEquals(-1,activationActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void getCovered() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        activationActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, activationActionSpace.getCovered());
        activationActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, activationActionSpace.getCovered());

    }

    @Test
    void getMinValueToPlaceFamiliar() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        assertEquals(1,activationActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void setBonus() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        activationActionSpace.setBonus(bonus);
        assertEquals(bonus, activationActionSpace.getBonus());

    }

    @Test
    void getBonus() {
        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
        assertEquals(new ArrayList<Gainable>(), activationActionSpace.getBonus());
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        activationActionSpace.setBonus(bonus);
        assertEquals(bonus, activationActionSpace.getBonus());
    }

    @Test
    void action1() {

    }

}