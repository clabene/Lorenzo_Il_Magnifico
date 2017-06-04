package test.playerTest;

import logic.player.VictoryPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class VictoryPointTest {
    @Test
    void getQuantity() {
        VictoryPoint victoryPoint = new VictoryPoint(3);
        assertEquals(3, victoryPoint.getQuantity());
    }

    @Test
    void setQuantity() {
        VictoryPoint victoryPoint = new VictoryPoint(3);
        victoryPoint.setQuantity(5);
        assertEquals(5, victoryPoint.getQuantity());
    }

    @Test
    void addVictoryPoints() {
        VictoryPoint victoryPoint = new VictoryPoint(3);
        victoryPoint.addVictoryPoints(2);
        assertEquals(5, victoryPoint.getQuantity());
    }

    @Test
    void loseVictoryPoints() {
        VictoryPoint victoryPoint = new VictoryPoint(3);
        victoryPoint.loseVictoryPoints(2);
        assertEquals(1, victoryPoint.getQuantity());
    }

    @Test
    void gainedByPlayer() {
    }

    @Test
    void lostByPlayer() {
    }

}