package test.pointsTracksTest;

import logic.pointsTracks.LandCardsPointsTrack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pinos on 04/06/2017.
 */
class LandCardsPointsTrackTest {
    @Test
    void incrementTrackPosition() {
        LandCardsPointsTrack landCardsPointsTrack = new LandCardsPointsTrack();
        landCardsPointsTrack.incrementTrackPosition();
        assertEquals(1, landCardsPointsTrack.getTrackPosition().getValue());
    }

    @Test
    void decrementTrackPosition() {
        LandCardsPointsTrack landCardsPointsTrack = new LandCardsPointsTrack();
        landCardsPointsTrack.incrementTrackPosition();
        landCardsPointsTrack.decrementTrackPosition();
        assertEquals(0, landCardsPointsTrack.getTrackPosition().getValue());

    }

    @Test
    void setTrackPosition() {
        LandCardsPointsTrack landCardsPointsTrack = new LandCardsPointsTrack();
        landCardsPointsTrack.setTrackPosition(4);
        assertEquals(4, landCardsPointsTrack.getTrackPosition().getValue());

    }


    @Test
    void calculateVictoryPointsFromPosition() {
        LandCardsPointsTrack landCardsPointsTrack = new LandCardsPointsTrack();
        landCardsPointsTrack.setTrackPosition(3);
        assertEquals(1, landCardsPointsTrack.calculateVictoryPointsFromPosition().getQuantity());

    }




}