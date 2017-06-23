package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import userInterface.gui.controllers.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 18/06/2017.
 */
public class LobbyDaCancellareController extends Controller {

    @FXML
    private Pane pane;
    @FXML
    private Button joinGameButton, newRoomButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeJoinGameButton();
        placeNewRoomButton();
    }


    private void placeJoinGameButton(){
        joinGameButton.layoutXProperty().bind(pane.widthProperty().divide(5));
        joinGameButton.layoutYProperty().bind(pane.heightProperty().divide(5));
        joinGameButton.setOnAction( e -> getGuiClient().joinGame() );
    }
    private void placeNewRoomButton(){
        newRoomButton.layoutXProperty().bind(pane.widthProperty().divide(5));
        newRoomButton.layoutYProperty().bind(pane.heightProperty().divide(5).add(50));
        newRoomButton.setOnAction( e -> getGuiClient().createNewRoom(2) ); //todo
    }

    public void successfullyJoinedGame(){
        //todo
    }

    public void successfullyCreatedRoom(){
        //todo
    }



}
