package test.pointsTracksTest;

import logic.pointsTracks.PersonCardsPointsTrack;
import logic.pointsTracks.PointsTrack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Pinos on 04/06/2017.
 */

class PersonCardsPointsTrackTest {
    @Test
    void incrementTrackPosition() {
        PersonCardsPointsTrack personCardsPointsTrack = new PersonCardsPointsTrack();
        personCardsPointsTrack.incrementTrackPosition();
        assertEquals(1, personCardsPointsTrack.getTrackPosition().getValue());
    }

    @Test
    void decrementTrackPosition() {
        PersonCardsPointsTrack personCardsPointsTrack = new PersonCardsPointsTrack();
        personCardsPointsTrack.incrementTrackPosition();
        personCardsPointsTrack.decrementTrackPosition();
        assertEquals(0, personCardsPointsTrack.getTrackPosition().getValue());

    }

    @Test
    void setTrackPosition() {
        PersonCardsPointsTrack personCardsPointsTrack = new PersonCardsPointsTrack();
        personCardsPointsTrack.setTrackPosition(4);
        assertEquals(4, personCardsPointsTrack.getTrackPosition().getValue());

    }


    @Test
    void calculateVictoryPointsFromPosition1() {
        PersonCardsPointsTrack personCardsPointsTrack = new PersonCardsPointsTrack();
        personCardsPointsTrack.setTrackPosition(6);
        assertEquals(21, personCardsPointsTrack.calculateVictoryPointsFromPosition().getQuantity());

    }



}