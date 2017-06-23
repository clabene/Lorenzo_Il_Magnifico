package userInterface.gui.component;

import logic.board.Color;

/**
 * Created by IBM on 22/06/2017.
 */
public interface PlayerTag {

    String getPlayerId();
    String getPlayerName();

    void addLandCard(String name);
    void addPersonCard(String name);
    void addBuildingCard(String name);
    void addVentureCard(String name);
    void updateWoodQuantity(String quantity);
    void updateStoneQuantity(String quantity);
    void updateSlaveQuantity(String quantity);
    void updateMoneyQuantity(String quantity);
    void updateVictoryPointsQuantity(String quantity);
    void updateMilitaryPointsQuantity(String quantity);
    void updateFaithPointsQuantity(String quantity);
    void updateFamilyMemberValue(Color color, String value);

}
