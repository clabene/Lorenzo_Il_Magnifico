package userInterface.gui.components;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import logic.board.Color;
import userInterface.gui.Loader;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by IBM on 20/06/2017.
 */
public class PlayerView extends Pane {

    private ConfigurationMode mode;


    private LabeledImageView[] images = new LabeledImageView[7];
    private final int WOOD_INDEX = 0;
    private final int STONE_INDEX = 1;
    private final int SLAVE_INDEX = 2;
    private final int MONEY_INDEX = 3;
    private final int VICTORY_INDEX = 4;
    private final int MILITARY_INDEX = 5;
    private final int FAITH_INDEX = 6;


    private Label[][] cardNames = new Label[4][6];
    private final int LAND_INDEX = 0;
    private final int PERSON_INDEX = 1;
    private final int BUILDING_INDEX = 2;
    private final int VENTURE_INDEX = 3;

    private int numberOfLandCards = 0;
    private int numberOfPersonCards = 0;
    private int numberOfBuildingCards = 0;
    private int numberOfVentureCards = 0;


    private FamilyMemberImageView[] familyMembers = new FamilyMemberImageView[4];
    private final int BLACK_INDEX = 0;
    private final int RED_INDEX = 1;
    private final int WHITE_INDEX = 2;
    private final int NEUTRAL_INDEX = 3;


    public PlayerView(String playerId, ConfigurationMode mode){
        this.mode = mode;
        initializeImages();
        initializeLabels();
        initializeFamilyMembers(playerId);
        placeComponents();

        for(LabeledImageView tmp : images)
            getChildren().addAll(tmp.getComponents());
        for(FamilyMemberImageView tmp : familyMembers)
            getChildren().addAll(tmp.getComponents());
        getChildren().addAll(cardNames[LAND_INDEX]);
        getChildren().addAll(cardNames[PERSON_INDEX]);
        getChildren().addAll(cardNames[BUILDING_INDEX]);
        getChildren().addAll(cardNames[VENTURE_INDEX]);
    }

    private void initializeImages(){
        //todo
        images[WOOD_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[STONE_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[SLAVE_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[MONEY_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[VICTORY_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[MILITARY_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");
        images[FAITH_INDEX] = new LabeledImageView("quantity: 0", "userInterface/gui/images/cancellami.jpg");

        //todo delete
        for(int i = 0; i <= FAITH_INDEX; i++){
            images[i].setFitWidth(70);
            images[i].setFitHeight(70);
        }

    }
    private void initializeLabels(){
        for (int i = 0; i<6; i++) {
            int a = i;
            cardNames[LAND_INDEX][i] = new Label("");
            cardNames[LAND_INDEX][i].setTextFill(Paint.valueOf("#009900"));
            cardNames[LAND_INDEX][i].setOnMouseClicked( e -> Loader.buildPopUp("Land Card", cardNames[LAND_INDEX][a].getText(), "userInterface/gui/images/cards/"+ cardNames[LAND_INDEX][a].getText() +".png"));

        }
        for (int i = 0; i<6; i++) {
            int a = i;
            cardNames[PERSON_INDEX][i] = new Label("");
            cardNames[PERSON_INDEX][i].setTextFill(Paint.valueOf("#0055cc"));
            cardNames[PERSON_INDEX][i].setOnMouseClicked( e -> Loader.buildPopUp("Person Card", cardNames[PERSON_INDEX][a].getText(), "userInterface/gui/images/cards/"+ cardNames[PERSON_INDEX][a].getText() +".png"));
        }
        for (int i = 0; i<6; i++) {
            int a = i;
            cardNames[BUILDING_INDEX][i] = new Label("");
            cardNames[BUILDING_INDEX][i].setTextFill(Paint.valueOf("#999900"));
            cardNames[BUILDING_INDEX][i].setOnMouseClicked( e -> Loader.buildPopUp("building Card", cardNames[BUILDING_INDEX][a].getText(), "userInterface/gui/images/cards/"+ cardNames[BUILDING_INDEX][a].getText() +".png"));
        }
        for (int i = 0; i<6; i++) {
            int a = i;
            cardNames[VENTURE_INDEX][i] = new Label("");
            cardNames[VENTURE_INDEX][i].setTextFill(Paint.valueOf("#880088"));
            cardNames[VENTURE_INDEX][i].setOnMouseClicked( e -> Loader.buildPopUp("Venture Card", cardNames[VENTURE_INDEX][a].getText(), "userInterface/gui/images/cards/"+ cardNames[VENTURE_INDEX][a].getText() +".png"));
        }
    }
    private void initializeFamilyMembers(String playerId){
        familyMembers[BLACK_INDEX] = new FamilyMemberImageView(playerId, Color.BLACK, 0);
        familyMembers[RED_INDEX] = new FamilyMemberImageView(playerId, Color.RED, 0);
        familyMembers[WHITE_INDEX] = new FamilyMemberImageView(playerId, Color.WHITE, 0);
        familyMembers[NEUTRAL_INDEX] = new FamilyMemberImageView(playerId, null, 0);

        //todo delete
        for(int i = 0; i <= NEUTRAL_INDEX; i++){
            familyMembers[i].setFitWidth(70);
            familyMembers[i].setFitHeight(70);
        }
    }

    private void placeComponents(){
        if(mode == ConfigurationMode.OPPONENT){


            getResourcesXProperty().bind(layoutXProperty());
            getPointsXProperty().bind(layoutXProperty());
            getLandCardXPosition().bind(layoutXProperty().add(200));
            getFamilyMembersXPosition().bind(getLandCardXPosition());

            getResourcesYProperty().bind(layoutYProperty());
            getPointsYProperty().bind(getResourcesYProperty().add(images[MONEY_INDEX].yProperty()).add(images[MONEY_INDEX].fitHeightProperty()).add(2));
            getLandCardYPosition().bind(layoutYProperty());
            getFamilyMembersYPosition().bind(layoutYProperty().add(cardNames[0][0].heightProperty().multiply(24)).add(4));

            placeResourceImagesOpponent();
            placePointsImagesOpponent();
            placeCardNamesOpponent();
            placeFamilyMembers();

        }
        else if(mode == ConfigurationMode.MY_PLAYER){
            placeResourceImagesMyPlayer();
            placePointsImagesMyPlayer();
            placeCardNamesMyPlayer();
            placeFamilyMembers();
        }
        else System.out.println("Not valid configuration given");
    }


    private void placeResourceImagesOpponent(){
        images[STONE_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty());
        images[STONE_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty().add(images[WOOD_INDEX].fitHeightProperty().add(2)));

        images[SLAVE_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty());
        images[SLAVE_INDEX].yProperty().bind(images[STONE_INDEX].yProperty().add(images[STONE_INDEX].fitHeightProperty().add(2)));

        images[MONEY_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty());
        images[MONEY_INDEX].yProperty().bind(images[SLAVE_INDEX].yProperty().add(images[SLAVE_INDEX].fitHeightProperty().add(2)));
    }
    private void placeResourceImagesMyPlayer(){
        images[STONE_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty());
        images[STONE_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty().add(images[WOOD_INDEX].fitHeightProperty().add(10)));

        images[SLAVE_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty().add(images[WOOD_INDEX].fitWidthProperty().add(110)));
        images[SLAVE_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty());

        images[MONEY_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty().add(images[WOOD_INDEX].fitWidthProperty().add(110)));
        images[MONEY_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty().add(images[WOOD_INDEX].fitHeightProperty().add(10)));
    }

    public DoubleProperty   getResourcesXProperty(){
        return images[WOOD_INDEX].xProperty();
    }
    public DoubleProperty getResourcesYProperty(){
        return images[WOOD_INDEX].yProperty();
    }


    private void placePointsImagesOpponent(){
        images[MILITARY_INDEX].xProperty().bind(images[VICTORY_INDEX].xProperty());
        images[MILITARY_INDEX].yProperty().bind(images[VICTORY_INDEX].yProperty().add(images[VICTORY_INDEX].fitHeightProperty().add(2)));

        images[FAITH_INDEX].xProperty().bind(images[VICTORY_INDEX].xProperty());
        images[FAITH_INDEX].yProperty().bind(images[MILITARY_INDEX].yProperty().add(images[MILITARY_INDEX].fitHeightProperty().add(2)));
    }
    private void placePointsImagesMyPlayer(){
        images[MILITARY_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty());
        images[MILITARY_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty().add(images[WOOD_INDEX].fitHeightProperty().add(10)));

        images[FAITH_INDEX].xProperty().bind(images[WOOD_INDEX].xProperty().add(images[WOOD_INDEX].fitWidthProperty().add(110)));
        images[FAITH_INDEX].yProperty().bind(images[WOOD_INDEX].yProperty());
    }

    public DoubleProperty getPointsXProperty(){
        return images[VICTORY_INDEX].xProperty();
    }
    public DoubleProperty getPointsYProperty(){
        return images[VICTORY_INDEX].yProperty();
    }


    private void placeCardNamesOpponent(){
        for(int i = 1; i < 6 ;i++){
            cardNames[LAND_INDEX][i].layoutXProperty().bind(cardNames[LAND_INDEX][0].layoutXProperty());
            cardNames[LAND_INDEX][i].layoutYProperty().bind(cardNames[LAND_INDEX][i-1].layoutYProperty().add(cardNames[LAND_INDEX][i-1].heightProperty()));
        }
        for(int CARD_TYPE_INDEX = 1; CARD_TYPE_INDEX<4; CARD_TYPE_INDEX++){
            for (int i = 0; i < 6; i++) {
                cardNames[CARD_TYPE_INDEX][i].layoutXProperty().bind(cardNames[LAND_INDEX][0].layoutXProperty());
                cardNames[CARD_TYPE_INDEX][i].layoutYProperty().bind(cardNames[LAND_INDEX][0].layoutYProperty()
                        .add(cardNames[CARD_TYPE_INDEX][i].heightProperty()
                                .multiply(i+CARD_TYPE_INDEX*6)
                        )

                );
            }
        }

    }
    private void placeCardNamesMyPlayer(){
        for(int i = 1; i < 6 ;i++){
            cardNames[LAND_INDEX][i].layoutXProperty().bind(cardNames[LAND_INDEX][0].layoutXProperty());
            cardNames[LAND_INDEX][i].layoutYProperty().bind(cardNames[LAND_INDEX][i-1].layoutYProperty().add(cardNames[LAND_INDEX][i-1].heightProperty().add(5)));
        }
        for(int i = 1; i < 6 ;i++){
            cardNames[PERSON_INDEX][i].layoutXProperty().bind(cardNames[PERSON_INDEX][0].layoutXProperty());
            cardNames[PERSON_INDEX][i].layoutYProperty().bind(cardNames[PERSON_INDEX][i-1].layoutYProperty().add(cardNames[PERSON_INDEX][i-1].heightProperty().add(5)));
        }
        for(int i = 1; i < 6 ;i++){
            cardNames[BUILDING_INDEX][i].layoutXProperty().bind(cardNames[BUILDING_INDEX][0].layoutXProperty());
            cardNames[BUILDING_INDEX][i].layoutYProperty().bind(cardNames[BUILDING_INDEX][i-1].layoutYProperty().add(cardNames[BUILDING_INDEX][i-1].heightProperty().add(5)));
        }
        for(int i = 1; i < 6 ;i++){
            cardNames[VENTURE_INDEX][i].layoutXProperty().bind(cardNames[VENTURE_INDEX][0].layoutXProperty());
            cardNames[VENTURE_INDEX][i].layoutYProperty().bind(cardNames[VENTURE_INDEX][i-1].layoutYProperty().add(cardNames[VENTURE_INDEX][i-1].heightProperty().add(5)));
        }
    }

    public DoubleProperty getLandCardXPosition(){
        return cardNames[LAND_INDEX][0].layoutXProperty();
    }
    public DoubleProperty getLandCardYPosition(){
        return cardNames[LAND_INDEX][0].layoutYProperty();
    }

    public DoubleProperty getPersonCardXPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[PERSON_INDEX][0].layoutXProperty();
    }
    public DoubleProperty getPersonCardYPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[PERSON_INDEX][0].layoutYProperty();
    }

    public DoubleProperty getBuildingCardXPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[BUILDING_INDEX][0].layoutXProperty();
    }
    public DoubleProperty getBuildingCardYPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[BUILDING_INDEX][0].layoutYProperty();
    }

    public DoubleProperty getVentureCardXPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[VENTURE_INDEX][0].layoutXProperty();
    }
    public DoubleProperty getVentureCardYPosition(){
        if(mode == ConfigurationMode.OPPONENT) return null;
        return cardNames[VENTURE_INDEX][0].layoutYProperty();
    }


    private void placeFamilyMembers(){
        familyMembers[RED_INDEX].xProperty().bind(familyMembers[BLACK_INDEX].xProperty());
        familyMembers[RED_INDEX].yProperty().bind(familyMembers[BLACK_INDEX].yProperty().add(familyMembers[BLACK_INDEX].fitHeightProperty().add(10)));

        familyMembers[WHITE_INDEX].xProperty().bind(familyMembers[BLACK_INDEX].xProperty().add(familyMembers[BLACK_INDEX].fitWidthProperty().multiply(1.5)));
        familyMembers[WHITE_INDEX].yProperty().bind(familyMembers[BLACK_INDEX].yProperty());

        familyMembers[NEUTRAL_INDEX].xProperty().bind(familyMembers[BLACK_INDEX].xProperty().add(familyMembers[BLACK_INDEX].fitWidthProperty().multiply(1.5)));
        familyMembers[NEUTRAL_INDEX].yProperty().bind(familyMembers[BLACK_INDEX].yProperty().add(familyMembers[BLACK_INDEX].fitHeightProperty().add(10)));

        int dim;
        if(mode == ConfigurationMode.MY_PLAYER) dim = 70;
        else dim = 30;
        for(int i = 0; i<=NEUTRAL_INDEX ;i++){
            familyMembers[i].setFitWidth(dim);
            familyMembers[i].setFitHeight(dim);
        }
    }

    public DoubleProperty getFamilyMembersXPosition(){
        return familyMembers[BLACK_INDEX].xProperty();
    }
    public DoubleProperty getFamilyMembersYPosition(){
        return familyMembers[BLACK_INDEX].yProperty();
    }


    public ArrayList<Node> getResourceImages(){
        ArrayList<Node> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, images[WOOD_INDEX].getComponents());
        Collections.addAll(toReturn, images[STONE_INDEX].getComponents());
        Collections.addAll(toReturn, images[SLAVE_INDEX].getComponents());
        Collections.addAll(toReturn, images[MONEY_INDEX].getComponents());
        return toReturn;
    }
    public ArrayList<Node> getPointImages(){
        ArrayList<Node> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, images[VICTORY_INDEX].getComponents());
        Collections.addAll(toReturn, images[MILITARY_INDEX].getComponents());
        Collections.addAll(toReturn, images[FAITH_INDEX].getComponents());
        return toReturn;
    }
    public Label[] getLandCardsName(){
        return cardNames[LAND_INDEX];
    }
    public Label[] getPersonCardsName(){
        return cardNames[PERSON_INDEX];
    }
    public Label[] getBuildingCardsName(){
        return cardNames[BUILDING_INDEX];
    }
    public Label[] getVentureCardsName(){
        return cardNames[VENTURE_INDEX];
    }
    public ArrayList<Node> getFamilyMembers(){
        ArrayList<Node> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, familyMembers[BLACK_INDEX].getComponents());
        Collections.addAll(toReturn, familyMembers[RED_INDEX].getComponents());
        Collections.addAll(toReturn, familyMembers[WHITE_INDEX].getComponents());
        Collections.addAll(toReturn, familyMembers[NEUTRAL_INDEX].getComponents());
        return toReturn;
    }


    public void addLandCard(String name){
        cardNames[LAND_INDEX][numberOfLandCards].setText(name);
        numberOfLandCards++;
    }
    public void addPersonCard(String name){
        cardNames[PERSON_INDEX][numberOfPersonCards].setText(name);
        numberOfPersonCards++;
    }
    public void addBuildingCard(String name){
        cardNames[BUILDING_INDEX][numberOfBuildingCards].setText(name);
        numberOfBuildingCards++;
    }
    public void addVentureCard(String name){
        cardNames[VENTURE_INDEX][numberOfVentureCards].setText(name);
        numberOfVentureCards++;
    }

    public void updateWoodQuantity(String quantity) {
        updateQuantity(WOOD_INDEX, quantity);
    }
    public void updateStoneQuantity(String quantity) {
        updateQuantity(STONE_INDEX, quantity);
    }
    public void updateSlaveQuantity(String quantity) {
        updateQuantity(SLAVE_INDEX, quantity);
    }
    public void updateMoneyQuantity(String quantity) {
        updateQuantity(MONEY_INDEX, quantity);
    }
    public void updateVictoryPointsQuantity(String quantity){
        updateQuantity(VICTORY_INDEX, quantity);
    }
    public void updateMilitaryPointsQuantity(String quantity){
        updateQuantity(MILITARY_INDEX, quantity);
    }
    public void updateFaithPointsQuantity(String quantity){
        updateQuantity(FAITH_INDEX, quantity);
    }

    private void updateQuantity(int INDEX, String quantity){
        images[INDEX].setText("quantity: "+quantity);
    }

    public void updateFamilyMemberValue(Color color, String value) {
        if(color == null){
            familyMembers[NEUTRAL_INDEX].toHide(false);
            familyMembers[NEUTRAL_INDEX].setValue(value);
            return;
        }
        switch (color) {
            case BLACK:
                familyMembers[BLACK_INDEX].toHide(false);
                familyMembers[BLACK_INDEX].setValue(value);
                break;
            case RED:
                familyMembers[RED_INDEX].toHide(false);
                familyMembers[RED_INDEX].setValue(value);
                break;
            case WHITE:
                familyMembers[WHITE_INDEX].toHide(false);
                familyMembers[WHITE_INDEX].setValue(value);
                break;
        }
    }
    public void hideFamilyMember(Color color) {
        if(color == null){
            familyMembers[NEUTRAL_INDEX].toHide(true);
            return;
        }
        switch (color) {
            case BLACK:
                familyMembers[BLACK_INDEX].toHide(true);
                break;
            case RED:
                familyMembers[RED_INDEX].toHide(true);
                break;
            case WHITE:
                familyMembers[WHITE_INDEX].toHide(true);
                break;
        }
    }

    public LabeledImageView getSlaveImageView(){
        return images[SLAVE_INDEX];
    }

    public enum ConfigurationMode{
        OPPONENT, MY_PLAYER;
    }


}
