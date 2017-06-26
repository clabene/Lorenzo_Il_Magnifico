package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.Slave;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 03/06/2017.
 */
class SlaveTest {
    Player player = new Player();
    @Test
    void resourceAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(0));
        assertEquals(0 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3));
        assertEquals(3 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(-3));
        assertEquals(3 ,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @Test
    void resourceSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3));
        player.getPlank().getSetOfResources().resourcesSpent(new Slave(3));
        assertEquals( 0 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Slave(4)));

    }

    @Test
    void getQuantity() {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3));
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
    }


}