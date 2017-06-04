package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 03/06/2017.
 */
class SetOfResourcesTest {
    Player player = new Player();

    @Test
    void getQuantityOfResources() {
        player.gain(new SetOfResources(new Money(0), new Slave(5), new Wood(3), new Stone(0)));
        assertEquals(8 ,player.getPlank().getSetOfResources().getQuantityOfResources());

    }

    @Test
    void getResources() {
        player.gain(new SetOfResources(new Money(0), new Slave(0), new Wood(0), new Stone(0)));
        assertArrayEquals(new Resource[]{new Wood(0), new Stone(0), new Slave(0), new Money(0)}, player.getPlank().getSetOfResources().getResources());
    }

    @Test
    void getQuantityOfSlaves() {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3), new Money(4), new Wood(5));
        assertEquals(3 ,player.getPlank().getSetOfResources().getQuantityOfSlaves());
    }

    @Test
    void resourcesAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Money(0), new Slave(5), new Wood(3), new Stone(0));
        assertEquals(8 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(5), new Slave(5), new Wood(3), new Stone(4));
        assertEquals(25 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(-6), new Slave(-11), new Wood(-7));
        assertEquals(25,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @Test
    void resourcesSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Slave(3), new Money(4), new Wood(5));
        player.getPlank().getSetOfResources().resourcesSpent(new Slave(2), new Money(4));
        assertEquals( 6 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Slave(4)));

    }

}