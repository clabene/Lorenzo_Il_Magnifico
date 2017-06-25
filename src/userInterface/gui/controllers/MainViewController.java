package userInterface.gui.controllers;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import userInterface.gui.components.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by IBM on 21/06/2017.
 */
public class MainViewController extends Controller {

    @FXML
    private Pane pane;

    @FXML
    private Accordion opponentsAccordion;

    @FXML
    private ImageView image;

    private BoardView board = new BoardView();
    private ArrayList<PlayerTag> players = new ArrayList<>();

    private BooleanProperty actionSpaceSelected = new SimpleBooleanProperty(false);
    private BooleanProperty familyMemberSelected = new SimpleBooleanProperty(false);

    public PlayerTag getPlayerFromId(String id){
        for(PlayerTag tmp : players)
            if(tmp.getPlayerId().equals(id))
                return tmp;
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImageV();
        new Trigger(this).start();

        placeBoard();
        placeOpponentsAccordion();
    }

    public void setImageV(){
        image.setImage(new Image("userInterface/gui/images/background.png"));
        image.setPreserveRatio(false);
        image.fitWidthProperty().bind(pane.widthProperty());
        image.fitHeightProperty().bind(pane.heightProperty());

    }


    /*
    * to be called right after initialize
    * */
    public void placeMyPlayer(){
        MyPlayerView myPlayer = new MyPlayerView(getGuiClient().getId(), "name goes here");
        players.add(myPlayer);
        myPlayer.layoutXProperty().bind(board.getXPosition().add(board.getWidthProperty().divide(2)));
        myPlayer.layoutYProperty().bind(pane.heightProperty().multiply(0.7));
        myPlayer.setBackground(new Background(new BackgroundFill(Color.TAN, new CornerRadii(40), null)));

        familyMemberSelected.bindBidirectional( myPlayer.getFamilyMemberSelectedProperty());
        slaveUsageSelected.bindBidirectional( myPlayer.getSlaveUsageSelectedProperty());

        pane.getChildren().add(myPlayer);
    }

    public ActionSpaceImageView getActionSpaceFromId(String id){
        return board.getActionSpaceFromId(id);
    }

    private BooleanProperty slaveUsageSelected = new SimpleBooleanProperty(false);

    private void placeOpponentsAccordion(){
        opponentsAccordion.layoutXProperty().bind(pane.widthProperty().multiply(0.7).add(70));
        opponentsAccordion.layoutYProperty().bind(board.getYPosition().add(20));
        opponentsAccordion.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.CHOCOLATE,null,null)));
    }
    private void placeBoard(){
        board.bindXPosition(pane.widthProperty().divide(30));
        board.bindYPosition(pane.heightProperty().divide(20));

        actionSpaceSelected.bindBidirectional(board.actionSpaceSelectedProperty());

        pane.getChildren().addAll(board.getComponents());
    }

    public void addOpponent(String playerId, String name) {
        OpponentView opponent = new OpponentView(playerId, name);
        players.add(opponent);
        opponentsAccordion.getPanes().add(opponent);
    }
    public void addFamilyMemberToActionSpace(String actionSpaceId, String playerId, logic.board.Color diceColor, Integer value){
        FamilyMemberImageView f = getActionSpaceFromId(actionSpaceId).addFamilyMemberImage(playerId, diceColor, value);
        pane.getChildren().add(f);
    }

/*
    public void addLandCard(String playerId, String name){
        getPlayerFromId(playerId).addLandCard(name);
    }
    public void addPersonCard(String playerId, String name){
        getPlayerFromId(playerId).addPersonCard(name);
    }
    public void addBuildingCard(String playerId, String name){
        getPlayerFromId(playerId).addBuildingCard(name);
    }
    public void addVentureCard(String playerId, String name){
        getPlayerFromId(playerId).addVentureCard(name);
    }
    public void updateWoodQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateWoodQuantity(quantity);
    }
    public void updateStoneQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateStoneQuantity( quantity);
    }
    public void updateSlaveQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateSlaveQuantity( quantity);
    }
    public void updateMoneyQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateMoneyQuantity( quantity);
    }
    public void updateVictoryPointsQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateVictoryPointsQuantity(quantity);
    }
    public void updateMilitaryPointsQuantity(String playerId, String quantity){
        getPlayerFromId(playerId).updateMilitaryPointsQuantity( quantity);
    }
    public void updateFaithPointsQuantity(String playerId, String quantity) {
        getPlayerFromId(playerId).updateFaithPointsQuantity( quantity);
    }
    public void updateFamilyMemberValue(String playerId, logic.board.Color color, String value){
        getPlayerFromId(playerId).updateFamilyMemberValue(color, value);
    }
*/


    private void buildSlavesPopUp() {
        Stage alertBox = new Stage();
        alertBox.setTitle("Slaves usage");
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/slavesPopUp.fxml"));
            Parent root = fxmlLoader.load();
            SlavesUsageController controller = fxmlLoader.getController();
            controller.setStage(alertBox);
            controller.setGuiClient(getGuiClient());
            alertBox.setOnCloseRequest( e -> getGuiClient().useSlaves(controller.getQuantity()) );
            alertBox.setScene(new Scene(root,400,275));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }


    private boolean notSupporting = true;
    private boolean canSend = false;
    public boolean getCanSend() {
        return canSend;
    }
    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }
    public boolean getNotSupporting() {
        return notSupporting;
    }
    public void setNotSupporting(boolean notSupporting) {
        this.notSupporting = notSupporting;
    }
    public void dealWithVatican(int periodNumber){
        Stage alertBox = new Stage();
        alertBox.setTitle("Vatican Inspection");
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vaticanInspection.fxml"));
            Parent root = fxmlLoader.load();
            VaticanInspectionController controller = fxmlLoader.getController();
            controller.setStage(alertBox);
            controller.setGuiClient(getGuiClient());
            //todo use period number to display the right tassel
            alertBox.setOnCloseRequest( e -> {
                notSupporting = controller.getNotSupporting();
                canSend = true;
            } );
            alertBox.setScene(new Scene(root,400,275));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }






    private class Trigger extends Thread{
        private MainViewController mainViewController;

        public Trigger(MainViewController m){
            mainViewController = m;
        }

        @Override
        public void run() {
            while(true){

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(mainViewController.actionSpaceSelected.get()) {
                    getGuiClient().selectActionSpace(board.getSelectedId());
                    mainViewController.actionSpaceSelected.set(false);
                }
                if(mainViewController.familyMemberSelected.get()){
                    getGuiClient().selectFamilyMember(((MyPlayerView)getPlayerFromId(getGuiClient().getId())).getSelectedFamilyMember());
                    mainViewController.familyMemberSelected.set(false);
                }
                if(mainViewController.slaveUsageSelected.get()){
                    Platform.runLater( () -> mainViewController.buildSlavesPopUp());
                    //while(mainViewController.getSlavesQuantity() == -1);
                    //getGuiClient().useSlaves(mainViewController.slavesQuantity);
                    //mainViewController.slavesQuantity = -1;
                    mainViewController.slaveUsageSelected.set(false);
                }
            }
        }
    }




}
