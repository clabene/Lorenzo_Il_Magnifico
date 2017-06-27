package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class WoodTest {
    Player player = new Player();
    @Test
    void resourceAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Wood(0));
        assertEquals(4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Wood(3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Wood(-3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @Test
    void resourceSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Wood(3));
        player.getPlank().getSetOfResources().resourcesSpent(new Wood(3));
        assertEquals( 4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Wood(4287504)));

    }

    @Test
    void getQuantity() {
        player.getPlank().getSetOfResources().resourcesAdded(new Wood(3));
        assertEquals(1003,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
    }



}