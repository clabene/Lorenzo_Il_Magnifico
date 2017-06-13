package test.actionSpacesTest;

import logic.actionSpaces.ActionSpaceType;
import logic.actionSpaces.CouncilActionSpace;
import logic.board.Color;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.resources.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */

class CouncilActionSpaceTest {
    @Test
    void action() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        Player player = new Player();
        Player player1 = new Player();
        ArrayList<String> names = new ArrayList<>();
        names.add("Claudio");
        councilActionSpace.action(player);
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );
        names.add("Pinò");
        councilActionSpace.action(player1);
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );
        councilActionSpace.action(player1);
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );
    }

    @Test
    void getFamilyMemberArrayList() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        Player player = new Player();
        Player player1 = new Player();
        ArrayList<String> names = new ArrayList<>();
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );
        names.add("Claudio");
        councilActionSpace.action(player);
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );
        names.add("Pinò");
        councilActionSpace.action(player1);
        assertEquals( names,councilActionSpace.getFamilyMemberArrayList() );

    }

    @Test
    void familyMemberAdded() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        councilActionSpace.familyMemberAdded(familyMember);
        assertEquals(false, councilActionSpace.getCovered());

    }

    @Test
    void familyMemberRemoved() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        councilActionSpace.familyMemberAdded(familyMember);
        assertEquals(false, councilActionSpace.getCovered());
        councilActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, councilActionSpace.getCovered());
    }

    @Test
    void getLastFamilyMemberAdded() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        FamilyMember familyMember1 = new FamilyMember(Color.RED, 3);
        FamilyMember familyMember2 = new FamilyMember(Color.WHITE, 3);
        councilActionSpace.familyMemberAdded(familyMember);
        councilActionSpace.familyMemberAdded(familyMember1);
        councilActionSpace.familyMemberAdded(familyMember2);
        assertEquals(familyMember2, councilActionSpace.getLastFamilyMemberAdded());
    }

    @Test
    void getActionSpaceType() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        assertEquals(ActionSpaceType.COUNCIL, councilActionSpace.getActionSpaceType());
    }

    @Test
    void decreaseMinValueRequested() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        assertEquals(1,councilActionSpace.getMinValueToPlaceFamiliar());
        councilActionSpace.decreaseMinValueRequested(1);
        assertEquals(0,councilActionSpace.getMinValueToPlaceFamiliar());
        councilActionSpace.decreaseMinValueRequested(1);
        assertEquals(-1,councilActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void getCovered() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        councilActionSpace.familyMemberAdded(familyMember);
        assertEquals(false, councilActionSpace.getCovered());
        councilActionSpace.familyMemberRemoved(familyMember);
        assertEquals(false, councilActionSpace.getCovered());
    }

    @Test
    void getMinValueToPlaceFamiliar() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        assertEquals(1,councilActionSpace.getMinValueToPlaceFamiliar());
    }

    @Test
    void setBonus() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        ArrayList<Gainable> bonus = new ArrayList<>();
        bonus.add(new Money(3));
        councilActionSpace.setBonus(bonus);
        assertEquals(bonus, councilActionSpace.getBonus());
    }

    @Test
    void getBonus() {
        CouncilActionSpace councilActionSpace = new CouncilActionSpace();
        ArrayList<Gainable> bonus = new ArrayList<>();

        bonus.add(new Money(1));
        bonus.add(new CouncilFavour(1));

        assertArrayEquals(bonus.toArray() , councilActionSpace.getBonus().toArray());
        /*
        ArrayList<Gainable> bonus1 = new ArrayList<>();
        bonus1.add(new Money(3));
        councilActionSpace.setBonus(bonus1);
        assertEquals(bonus1, councilActionSpace.getBonus());*/
    }

    @Test
    void action1() {
    }

}