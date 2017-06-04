package test.pointsTracksTest;

import logic.pointsTracks.MilitaryPointsTrack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class MilitaryPointsTrackTest {
    @Test
    void incrementTrackPosition() {
        MilitaryPointsTrack militaryPointsTrack = new MilitaryPointsTrack();
        militaryPointsTrack.incrementTrackPosition();
        assertEquals(1, militaryPointsTrack.getTrackPosition().getValue());
    }

    @Test
    void decrementTrackPosition() {
        MilitaryPointsTrack militaryPointsTrack = new MilitaryPointsTrack();
        militaryPointsTrack.incrementTrackPosition();
        militaryPointsTrack.decrementTrackPosition();
        assertEquals(0, militaryPointsTrack.getTrackPosition().getValue());

    }

    @Test
    void setTrackPosition() {
        MilitaryPointsTrack militaryPointsTrack = new MilitaryPointsTrack();
        militaryPointsTrack.setTrackPosition(4);
        assertEquals(4, militaryPointsTrack.getTrackPosition().getValue());

    }

    @Test
    void calculateVictoryPointsFromPosition() {


    }

}