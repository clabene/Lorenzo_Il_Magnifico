package test.pointsTracksTest;

import logic.pointsTracks.FaithPointsTrack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class FaithPointsTrackTest {
    @Test
    void incrementTrackPosition() {
        FaithPointsTrack faithPointsTrack = new FaithPointsTrack();
        faithPointsTrack.incrementTrackPosition();
        assertEquals(1, faithPointsTrack.getTrackPosition().getValue());
    }

    @Test
    void decrementTrackPosition() {
        FaithPointsTrack faithPointsTrack = new FaithPointsTrack();
        faithPointsTrack.incrementTrackPosition();
        faithPointsTrack.decrementTrackPosition();
        assertEquals(0, faithPointsTrack.getTrackPosition().getValue());
    }

    @Test
    void setTrackPosition() {
        FaithPointsTrack faithPointsTrack = new FaithPointsTrack();
        faithPointsTrack.setTrackPosition(4);
        assertEquals(4, faithPointsTrack.getTrackPosition().getValue());
    }



    @Test
    void calculateVictoryPointsFromPosition() {
        FaithPointsTrack faithPointsTrack = new FaithPointsTrack();
        faithPointsTrack.setTrackPosition(7);
        assertEquals(9, faithPointsTrack.calculateVictoryPointsFromPosition().getQuantity());

    }



}