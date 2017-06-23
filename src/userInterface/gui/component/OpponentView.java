package userInterface.gui.component;


import javafx.scene.control.TitledPane;
import logic.board.Color;
import userInterface.PlayerColor;

/**
 * Created by IBM on 21/06/2017.
 */
public class OpponentView extends TitledPane implements PlayerTag {

    private PlayerView player;
    private String name;
    private String id;

    public OpponentView(PlayerColor playerColor, String playerId, String playerName){
        player = new PlayerView(playerColor, PlayerView.ConfigurationMode.OPPONENT);
        this.id = playerId;
        this.name = playerName;
        setText(playerName);
        setContent(player);
    }

    public void addLandCard(String name){
        player.addLandCard(name);
    }
    public void addPersonCard(String name){
        player.addPersonCard(name);
    }
    public void addBuildingCard(String name){
        player.addBuildingCard(name);
    }
    public void addVentureCard(String name){
        player.addVentureCard(name);
    }

    public void updateWoodQuantity(String quantity){
        player.updateWoodQuantity(quantity);
    }
    public void updateStoneQuantity(String quantity){
        player.updateStoneQuantity( quantity);
    }
    public void updateSlaveQuantity(String quantity){
        player.updateSlaveQuantity( quantity);
    }
    public void updateMoneyQuantity(String quantity){
        player.updateMoneyQuantity( quantity);
    }
    public void updateVictoryPointsQuantity(String quantity){
        player.updateVictoryPointsQuantity(quantity);
    }
    public void updateMilitaryPointsQuantity(String quantity){
        player.updateMilitaryPointsQuantity( quantity);
    }
    public void updateFaithPointsQuantity(String quantity){
        player.updateFaithPointsQuantity( quantity);
    }

    public void updateFamilyMemberValue(Color color, String value) {
        player.updateFamilyMemberValue(color, value);
    }

    public String getPlayerId(){
        return id;
    }
    public String getPlayerName(){
        return name;
    }

}
