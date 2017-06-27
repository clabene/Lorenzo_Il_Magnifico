package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.Slave;
import logic.resources.Stone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class StoneTest {
    Player player = new Player();
    @Test
    void resourceAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Stone(0));
        assertEquals(4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Stone(3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Stone(-3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @Test
    void resourceSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Stone(3));
        player.getPlank().getSetOfResources().resourcesSpent(new Stone(3));
        assertEquals( 4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Stone(44875)));

    }

    @Test
    void getQuantity() {
        player.getPlank().getSetOfResources().resourcesAdded(new Stone(3));
        assertEquals(1003,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
    }



}