package userInterface.gui.controllers;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import logic.actionSpaces.ActionSpace;
import logic.interfaces.Gainable;
import userInterface.gui.ColorHandler;
import userInterface.gui.Loader;
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

    //@FXML
    private Accordion opponentsAccordion = new Accordion();

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

    public Accordion getOpponentsAccordion() {
        return opponentsAccordion;
    }

    public void setImageV(){
        image.setImage(new Image("userInterface/gui/images/background.png"));
        image.setPreserveRatio(false);
        image.fitWidthProperty().bind(pane.widthProperty());
        image.fitHeightProperty().bind(pane.heightProperty());
    }

    public void setTassels(String a, String b, String c){
        board.setImages(a,b,c);

    }

    public void setDice(int a, int b , int c){
        board.setDice(a,b,c);
    }


    /*
    * to be called right after initialize
    * */
    public void placeMyPlayer(){
        ColorHandler.addPlayer(getGuiClient().getId());
        MyPlayerView myPlayer = new MyPlayerView(getGuiClient().getId());
        players.add(myPlayer);
        myPlayer.layoutXProperty().bind(board.getXPosition().add(board.getWidthProperty().divide(2)));
        myPlayer.layoutYProperty().bind(pane.heightProperty().multiply(0.77));
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
        opponentsAccordion.layoutYProperty().bind(board.getYPosition().subtract(12));
        opponentsAccordion.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.CHOCOLATE,null,null)));
        opponentsAccordion.toFront();
        pane.getChildren().add(opponentsAccordion);
    }
    private void placeBoard(){
        board.bindXPosition(pane.widthProperty().divide(30));
        board.bindYPosition(pane.heightProperty().divide(20));

        actionSpaceSelected.bindBidirectional(board.actionSpaceSelectedProperty());

        pane.getChildren().addAll(board.getComponents());
    }

    public void addOpponent(String playerId) {
        ColorHandler.addPlayer(playerId);
        OpponentView opponent = new OpponentView(playerId, ColorHandler.getColor(playerId)+"_Player");
        players.add(opponent);
        opponentsAccordion.getPanes().add(opponent);
    }
    public void addFamilyMemberToActionSpace(String actionSpaceId, String playerId, logic.board.Color diceColor, Integer value){
        FamilyMemberImageView f = getActionSpaceFromId(actionSpaceId).addFamilyMemberImage(playerId, diceColor, value);
        pane.getChildren().add(f);
    }
    public void removeFamilyMemberFromActionSpace(String actionSpaceId ){
        //ActionSpaceImageView a = getActionSpaceFromId(actionSpaceId).getComponents();
        pane.getChildren().removeAll(getActionSpaceFromId(actionSpaceId).getComponents());
        for(FamilyMemberImageView tmp : getActionSpaceFromId(actionSpaceId).getFamilyMembers()) {
            pane.getChildren().removeAll(tmp.getComponents());
            pane.getChildren().removeAll(tmp);
        }

        if(getActionSpaceFromId(actionSpaceId) instanceof TowerActionSpaceImageView){
            TowerActionSpaceImageView a = (TowerActionSpaceImageView) getActionSpaceFromId(actionSpaceId);
            pane.getChildren().addAll(a.getComponents());
        }
        else
            pane.getChildren().add(getActionSpaceFromId(actionSpaceId));





            /*
            tmp.getComponents()[0].setVisible(false);
            tmp.getComponents()[1].setVisible(false);
            tmp.getComponents()[2].setVisible(false);
        }*/
        //pane.getChildren().removeAll(getActionSpaceFromId(actionSpaceId));

        //         pane.getChildren().add(getActionSpaceFromId(actionSpaceId));

        //pane.getChildren().add(getActionSpaceFromId(actionSpaceId).getComponents().remove(1));





        //FamilyMemberImageView f = getActionSpaceFromId(actionSpaceId).addFamilyMemberImage(playerId, diceColor, value);

    }




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


    private boolean canSendVatican = false;
    public boolean getCanSendVatican() {
        return canSendVatican;
    }
    public void setCanSendVatican(boolean canSend) {
        this.canSendVatican = canSend;
    }

    private boolean notSupporting = true;
    public boolean getNotSupporting() {
        return notSupporting;
    }
    public void setNotSupporting(boolean notSupporting) {
        this.notSupporting = notSupporting;
    }
    public void dealWithVatican(int periodNumber){
        canSendVatican = false;
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
            alertBox.setOnCloseRequest( e -> {
                notSupporting = controller.getNotSupporting();
                canSendVatican = true;
            } );
            alertBox.setScene(new Scene(root,400,275));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }


    private boolean canSendFavour = false;
    public boolean getCanSendFavour() {
        return canSendFavour;
    }
    public void setCanSendFavour(boolean canSend) {
        this.canSendFavour = canSend;
    }

    private Gainable[] favours;
    public void setFavours(Gainable[] gainables) {
        this.favours = gainables;
    }
    public Gainable[] getFavours() {
        return favours;
    }
    public void selectCouncilFavour(int numberOfFavours){
        canSendFavour = false;
        Stage alertBox = new Stage();
        alertBox.setTitle("Council favour");
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/councilFavourPopUp.fxml"));
            Parent root = fxmlLoader.load();
            CouncilFavourPopUpController controller = fxmlLoader.getController();
            controller.setStage(alertBox);
            controller.setGuiClient(getGuiClient());
            controller.setNumberOfFavours(numberOfFavours);
            alertBox.setOnCloseRequest( e -> {
                favours = controller.getFavours();
                canSendFavour = true;
            } );
            alertBox.setScene(new Scene(root,400,275));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }



    private boolean canSendAction = false;
    public boolean getCanSendAction() {
        return canSendAction;
    }
    public void setCanSendAction(boolean canSend) {
        this.canSendAction = canSend;
    }

    private ActionSpace actionSpace;
    public ActionSpace getActionSpace() {
        return actionSpace;
    }
    public void setActionSpace(ActionSpace actionSpace) {
        this.actionSpace = actionSpace;
    }
    public void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces){
        canSendAction = false;
        Stage alertBox = new Stage();
        alertBox.setTitle("Extra Action");
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/extraActionPopUp.fxml"));
            Parent root = fxmlLoader.load();
            ExtraActionPopUpController controller = fxmlLoader.getController();
            controller.setStage(alertBox);
            controller.setGuiClient(getGuiClient());
            controller.setActionSpaces(actionSpaces);
            alertBox.setOnCloseRequest( e -> {
                actionSpace = controller.getActionSpace();
                canSendAction = true;
            } );
            alertBox.setScene(new Scene(root,400,275));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }

    public void showWinner(String winnerId){
        for(PlayerTag tmp : players) {
            if (tmp.getPlayerId().equals(winnerId)) {
                Loader.buildPopUp( "WINNER","The winner is "+getPlayerFromId(winnerId).getPlayerName(), "userInterface/gui/images/winner.png" );
            }
        }
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
