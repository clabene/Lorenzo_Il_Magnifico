package test.playerTest;

import logic.board.Color;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class FamilyMemberTest {
    @Test
    void getPlayerId() {
        Player player = new Player(new Wood());
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3, player.getId(), false);
        assertEquals("Claudio", familyMember.getPlayerId() );

    }

    @Test
    void getValue() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        assertEquals(3, familyMember.getValue());
    }

    @Test
    void getColor() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        assertEquals(Color.BLACK, familyMember.getColor());
    }

    @Test
    void valueIncremented() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        assertEquals(3, familyMember.getValue());
        familyMember.valueIncremented();
        assertEquals(4, familyMember.getValue());

    }

    @Test
    void setInActionSpace() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        familyMember.setInActionSpace(true);
        assertEquals(true, familyMember.getInActionSpace());
    }

    @Test
    void getInActionSpace() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        assertEquals(false, familyMember.getInActionSpace());
        familyMember.setInActionSpace(true);
        assertEquals(true, familyMember.getInActionSpace());
    }

    @Test
    void incrementFamilyMemberValue() {
        FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);
        assertEquals(3, familyMember.getValue());
        familyMember.incrementFamilyMemberValue(3);
        assertEquals(6, familyMember.getValue());
    }

}