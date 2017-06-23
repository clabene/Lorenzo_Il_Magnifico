package userInterface.gui.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import logic.board.Color;
import logic.player.FamilyMember;
import userInterface.PlayerColor;

/**
 * Created by IBM on 21/06/2017.
 */
public class MyPlayerView extends TabPane implements PlayerTag {

    private PlayerView me;
    private String id;
    private String name;

    private Tab[] tabs = new Tab[7];

    private BooleanProperty familyMemberSelected = new SimpleBooleanProperty(false);
    private FamilyMember selectedFamilyMember;

    public BooleanProperty getFamilyMemberSelectedProperty() {
        return familyMemberSelected;
    }
    public FamilyMember getSelectedFamilyMember() {
        return selectedFamilyMember;
    }


    public MyPlayerView(String playerId, String playerName){
        me = new PlayerView(PlayerColor.RED, PlayerView.ConfigurationMode.MY_PLAYER); //todo client should be able to pick a color
        id = playerId;
        name = playerName;
        initializeTabs();
        setFamilyMemberSelectable();
        setSlavesSelectable();
        getTabs().addAll(tabs);
    }

    public String getPlayerName() {
        return name;
    }

    public String getPlayerId() {
        return id;
    }
    private void initializeTabs(){
        initializeResourcesTab();
        initializePointsTab();
        initializeLandsTab();
        initializePersonsTab();
        initializeBuildingsTab();
        initializeVenturesTab();
        initializeFamilyMembersTab();
    }
    private void initializeResourcesTab(){
        Pane p = new Pane((Node[]) me.getResourceImages().toArray(new Node[me.getResourceImages().size()]));
        me.getResourcesXProperty().bind(widthProperty().divide(11));
        me.getResourcesYProperty().set(5);
        tabs[0] = new Tab("Resources", p);
        tabs[0].setClosable(false);
    }
    private void initializePointsTab(){
        Pane p = new Pane((Node[]) me.getPointImages().toArray( new Node[me.getPointImages().size()] ));
        me.getPointsXProperty().bind(widthProperty().divide(11));
        me.getPointsYProperty().set(5);
        tabs[1] = new Tab("Points", p);
        tabs[1].setClosable(false);
    }
    private void initializeLandsTab(){
        Pane p = new Pane(me.getLandCardsName());
        me.getLandCardXPosition().bind(widthProperty().divide(2).subtract(50));
        me.getLandCardYPosition().set(10);
        tabs[2] = new Tab("Lands", p);
        tabs[2].setClosable(false);
    }
    private void initializePersonsTab(){
        Pane p = new Pane(me.getPersonCardsName());
        me.getPersonCardXPosition().bind(widthProperty().divide(2).subtract(50));
        me.getPersonCardYPosition().set(10);
        tabs[3] = new Tab("Persons", p);
        tabs[3].setClosable(false);
    }
    private void initializeBuildingsTab(){
        Pane p = new Pane(me.getBuildingCardsName());
        me.getBuildingCardXPosition().bind(widthProperty().divide(2).subtract(50));
        me.getBuildingCardYPosition().set(10);
        tabs[4] = new Tab("Buildings", p);
        tabs[4].setClosable(false);
    }
    private void initializeVenturesTab(){
        Pane p = new Pane(me.getVentureCardsName());
        me.getVentureCardXPosition().bind(widthProperty().divide(2).subtract(50));
        me.getVentureCardYPosition().set(10);
        tabs[5] = new Tab("Ventures", p);
        tabs[5].setClosable(false);
    }


    private void initializeFamilyMembersTab(){
        Pane p = new Pane(me.getFamilyMembers().toArray(new Node[me.getFamilyMembers().size()]));
        me.getFamilyMembersXPosition().bind(widthProperty().divide(11));
        me.getFamilyMembersYPosition().set(5);
        tabs[6] = new Tab("Family Members", p);
        tabs[6].setClosable(false);
    }
    public void addLandCard(String name){
        me.addLandCard(name);
    }
    public void addPersonCard(String name){
        me.addPersonCard(name);
    }
    public void addBuildingCard(String name){
        me.addBuildingCard(name);
    }

    public void addVentureCard(String name){
        me.addVentureCard(name);
    }
    public void updateWoodQuantity(String quantity){
        me.updateWoodQuantity(quantity);
    }
    public void updateStoneQuantity(String quantity){
        me.updateStoneQuantity( quantity);
    }
    public void updateSlaveQuantity(String quantity){
        me.updateSlaveQuantity( quantity);
    }
    public void updateMoneyQuantity(String quantity){
        me.updateMoneyQuantity( quantity);
    }
    public void updateVictoryPointsQuantity(String quantity){
        me.updateVictoryPointsQuantity(quantity);
    }
    public void updateMilitaryPointsQuantity(String quantity){
        me.updateMilitaryPointsQuantity( quantity);
    }
    public void updateFaithPointsQuantity(String quantity){
        me.updateFaithPointsQuantity( quantity);
    }


    public void updateFamilyMemberValue(Color color, String value) {
        me.updateFamilyMemberValue(color, value);
    }

    private void setFamilyMemberSelectable(){
        for(Node tmp : me.getFamilyMembers()){
            if(!(tmp instanceof FamilyMemberImageView)) continue;
            tmp.setOnMouseClicked(e -> {
                familyMemberSelected.set(true);
                FamilyMemberImageView f  = (FamilyMemberImageView) tmp;
                selectedFamilyMember = new FamilyMember(f.getDiceColor(), f.getValue(), id );
                });
        }
    }

    private BooleanProperty slaveUsageSelected = new SimpleBooleanProperty(false);

    private void setSlavesSelectable(){
        me.getSlaveImageView().setOnMouseClicked( e -> {
            System.out.println(slaveUsageSelected.get());
            slaveUsageSelected.set(true);
            System.out.println(slaveUsageSelected.get());
        } );
    }

    public BooleanProperty getSlaveUsageSelectedProperty() {
        return slaveUsageSelected;
    }
}
