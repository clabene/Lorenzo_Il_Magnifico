package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.Money;
import logic.resources.Slave;
import logic.resources.Stone;
import logic.resources.Wood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 03/06/2017.
 */
class ResourceTest {
    Player player = new Player();
    @Test
    void resourceAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Money(0), new Slave(5), new Wood(3), new Stone(0));
        assertEquals(8 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(5), new Slave(5), new Wood(3), new Stone(4));
        assertEquals(25 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(-6), new Slave(-11), new Wood(-7));
        assertEquals(25,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @Test
    void resourceSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3), new Money(4), new Wood(5));
        player.getPlank().getSetOfResources().resourcesSpent(new Slave(2), new Money(4));
        assertEquals( 6 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Slave(4)));

    }

    @Test
    void getQuantity() {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3), new Wood(2), new Money(4), new Stone(5));
        assertEquals(2,player.getPlank().getSetOfResources().getResources()[0].getQuantity());
        assertEquals(5,player.getPlank().getSetOfResources().getResources()[1].getQuantity());
        assertEquals(3,player.getPlank().getSetOfResources().getResources()[2].getQuantity());
        assertEquals(4,player.getPlank().getSetOfResources().getResources()[3].getQuantity());
    }


}