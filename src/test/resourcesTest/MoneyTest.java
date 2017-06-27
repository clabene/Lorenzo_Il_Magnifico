package test.resourcesTest;

import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;
import logic.resources.Money;
import logic.resources.SetOfResources;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 03/06/2017.
 */
class MoneyTest {
    Player player = new Player();
    @org.junit.jupiter.api.Test
    void resourceAdded() {
        player.getPlank().getSetOfResources().resourcesAdded(new Money(0));
        assertEquals(4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        player.getPlank().getSetOfResources().resourcesAdded(new Money(-3));
        assertEquals(4003 ,player.getPlank().getSetOfResources().getQuantityOfResources());
    }

    @org.junit.jupiter.api.Test
    void resourceSpent() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Money(3));
        player.getPlank().getSetOfResources().resourcesSpent(new Money(3));
        assertEquals( 4000 ,player.getPlank().getSetOfResources().getQuantityOfResources());
        assertThrows(NegativeResourceQuantityException.class, () -> player.getPlank().getSetOfResources().resourcesSpent(new Money(4000)));

    }

    @org.junit.jupiter.api.Test
    void getQuantity() throws NegativeResourceQuantityException {
        player.getPlank().getSetOfResources().resourcesAdded(new Money(3));
        assertEquals(1003,player.getPlank().getSetOfResources().getResources()[3].getQuantity());

    }


}