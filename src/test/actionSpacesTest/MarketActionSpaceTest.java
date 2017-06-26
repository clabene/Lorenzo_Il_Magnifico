package test.actionSpacesTest;

import logic.actionSpaces.ActionSpaceType;
import logic.actionSpaces.MarketActionSpace;
import logic.board.Color;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class MarketActionSpaceTest {
    @Test
    void action() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        Player player = new Player();
        assertTrue(marketActionSpace.action(player));
    }

    @Test
    void familyMemberAdded() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        marketActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, marketActionSpace.getCovered());
    }

    @Test
    void familyMemberRemoved() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        marketActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, marketActionSpace.getCovered());
        marketActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, marketActionSpace.getCovered());
    }

    @Test
    void getLastFamilyMemberAdded() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);

        marketActionSpace.familyMemberAdded(familyMember);

        assertEquals(familyMember, marketActionSpace.getLastFamilyMemberAdded());
    }

    @Test
    void getActionSpaceType() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        assertEquals(ActionSpaceType.MARKET, marketActionSpace.getActionSpaceType());
    }

    @Test
    void decreaseMinValueRequested() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        assertEquals(1,marketActionSpace.getMinValueToPlaceFamiliar());
        marketActionSpace.decreaseMinValueRequested(1);
        assertEquals(0,marketActionSpace.getMinValueToPlaceFamiliar());
        marketActionSpace.decreaseMinValueRequested(1);
        assertEquals(-1,marketActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void getCovered() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        marketActionSpace.familyMemberAdded(familyMember);
        assertEquals(true, marketActionSpace.getCovered());
        marketActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, marketActionSpace.getCovered());
    }

    @Test
    void getMinValueToPlaceFamiliar() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        assertEquals(1,marketActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void setBonus() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        marketActionSpace.setBonus(bonus);
        assertEquals(bonus, marketActionSpace.getBonus());
    }

    @Test
    void getBonus() {
        MarketActionSpace marketActionSpace = new MarketActionSpace();
        assertEquals(new ArrayList<Gainable>(), marketActionSpace.getBonus());
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        marketActionSpace.setBonus(bonus);
        assertEquals(bonus, marketActionSpace.getBonus());
    }

    @Test
    void action1() {
    }

}