package userInterface.gui.components;


import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import logic.board.Color;

/**
 * Created by IBM on 21/06/2017.
 */
public class OpponentView extends TitledPane implements PlayerTag {

    private PlayerView player;
    private String name;
    private String id;

    public OpponentView(String playerId, String playerName){
        player = new PlayerView(playerId, PlayerView.ConfigurationMode.OPPONENT);
        this.id = playerId;
        this.name = playerName;
        setText(playerName);
        setContent(player);

    }

    public void addLandCard(String name){
        player.addLandCard(name);
    }

    @Override
    public void setLandCard(int index, String name) {
        if(name == null) player.getLandCardsName()[index].setText("");
        else player.getLandCardsName()[index].setText(name);
    }

    public void addPersonCard(String name){
        player.addPersonCard(name);
    }

    @Override
    public void setPersonCard(int index, String name) {
        if(name == null) player.getPersonCardsName()[index].setText("");
        else player.getPersonCardsName()[index].setText(name);
    }

    public void addBuildingCard(String name){
        player.addBuildingCard(name);
    }

    @Override
    public void setBuildingCard(int index, String name) {
        if(name == null) player.getBuildingCardsName()[index].setText("");
        else player.getBuildingCardsName()[index].setText(name);
    }

    public void addVentureCard(String name){
        player.addVentureCard(name);
    }

    @Override
    public void setVentureCard(int index, String name) {
        if(name == null) player.getVentureCardsName()[index].setText("");
        else player.getVentureCardsName()[index].setText(name);
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
    public void hideFamilyMember(Color color){
        player.hideFamilyMember(color);
    }

    public String getPlayerId(){
        return id;
    }
    public String getPlayerName(){
        return name;
    }



}
