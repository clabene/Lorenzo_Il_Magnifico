package userInterface.gui.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

import javafx.scene.control.Accordion;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import userInterface.PlayerColor;
import userInterface.gui.component.*;

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

    private BoardView board = new BoardView();
    private ArrayList<PlayerTag> players = new ArrayList<>();

    private BooleanProperty actionSpaceSelected = new SimpleBooleanProperty(false);
    private BooleanProperty familyMemberSelected = new SimpleBooleanProperty(false);
    private BooleanProperty slaveUsageSelected = new SimpleBooleanProperty(false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Trigger(this).start();

        placeBoard();
        placeOpponentsAccordion();
    }


    public PlayerTag getPlayerFromId(String id){
        for(PlayerTag tmp : players)
            if(tmp.getPlayerId().equals(id))
                return tmp;
        return null;
    }

    /*
    * to be called right after initialize(...)
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

    private void placeOpponentsAccordion(){
        opponentsAccordion.layoutXProperty().bind(pane.widthProperty().multiply(0.7));
        opponentsAccordion.layoutYProperty().bind(board.getYPosition().add(20));
    }
    private void placeBoard(){
        board.bindXPosition(pane.widthProperty().divide(30));
        board.bindYPosition(pane.heightProperty().divide(20));

        actionSpaceSelected.bindBidirectional(board.actionSpaceSelectedProperty());

        pane.getChildren().addAll(board.getComponents());
    }

    /*
    public void addPlayer(PlayerColor playerColor, String playerId, String name){
        if(playerId.equals(getGuiClient().getId())) placeMyPlayer();
        else addOpponent( playerColor,  playerId,  name);
    }
    */
    public void addOpponent(PlayerColor playerColor, String playerId, String name) {
        OpponentView opponent = new OpponentView(playerColor, playerId, name);
        players.add(opponent);
        opponentsAccordion.getPanes().add(opponent);
    }
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
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateWoodQuantity(quantity);
    }
    public void updateStoneQuantity(String playerId, String quantity){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateStoneQuantity( quantity);
    }
    public void updateSlaveQuantity(String playerId, String quantity){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateSlaveQuantity( quantity);
    }
    public void updateMoneyQuantity(String playerId, String quantity){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateMoneyQuantity( quantity);
    }
    public void updateVictoryPointsQuantity(String playerId, String quantity){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateVictoryPointsQuantity(quantity);
    }
    public void updateMilitaryPointsQuantity(String playerId, String quantity){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateMilitaryPointsQuantity( quantity);
    }
    public void updateFaithPointsQuantity(String playerId, String quantity) {
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateFaithPointsQuantity( quantity);
    }
    public void updateFamilyMemberValue(String playerId, logic.board.Color color, String value){
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss " + playerId + getPlayerFromId(playerId));
        getPlayerFromId(playerId).updateFamilyMemberValue(color, value);
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
                    getGuiClient().useSlaves(1);
                    mainViewController.slaveUsageSelected.set(false);
                }
            }
        }
    }




}
