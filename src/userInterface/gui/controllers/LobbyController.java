package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 18/06/2017.
 */
public class LobbyController extends Controller {

    @FXML
    private Pane pane;
    @FXML
    private Button joinGameButton, newRoomButton2, newRoomButton3,newRoomButton4;

    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inizializeImage();
        placeJoinGameButton();
        placeNewRoomButton2();
        placeNewRoomButton3();
        placeNewRoomButton4();

    }

    public void inizializeImage(){

        pane.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE,null,null)));
        /*
        image = new ImageView();
        image.setImage(new Image("userInterface/gui/images/sfondo_firenze.jpg"));
        image.setX(pane.getLayoutX());
        image.setY(pane.getLayoutY());
        image.setFitWidth(pane.getPrefWidth());
        image.setFitHeight(pane.getPrefHeight());
        pane.getChildren().add(image);*/

    }


    private void placeJoinGameButton(){
        joinGameButton.layoutXProperty().bind(pane.widthProperty().divide(5));
        joinGameButton.layoutYProperty().bind(pane.heightProperty().divide(5));
        joinGameButton.setOnAction( e -> getGuiClient().joinGame() );

    }
    private void placeNewRoomButton2(){
        newRoomButton2.layoutXProperty().bind(pane.widthProperty().divide(5));
        newRoomButton2.layoutYProperty().bind(pane.heightProperty().divide(5).add(50));
        newRoomButton2.setOnAction( e -> getGuiClient().createNewRoom(2) );
       //todo
    }
    private void placeNewRoomButton3(){
        newRoomButton3.layoutXProperty().bind(pane.widthProperty().divide(5));
        newRoomButton3.layoutYProperty().bind(pane.heightProperty().divide(5).add(100));
        newRoomButton3.setOnAction( e -> getGuiClient().createNewRoom(3) );
       //todo
    }
    private void placeNewRoomButton4(){
        newRoomButton4.layoutXProperty().bind(pane.widthProperty().divide(5));
        newRoomButton4.layoutYProperty().bind(pane.heightProperty().divide(5).add(150));
        newRoomButton4.setOnAction( e -> getGuiClient().createNewRoom(4) ); //todo

    }





    public void successfullyJoinedGame(){
        //todo
    }

    public void successfullyCreatedRoom(){
        //todo
    }



}
