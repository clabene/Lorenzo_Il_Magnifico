package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 18/06/2017.
 */
public class LogInDaCancellareController extends Controller {

    @FXML
    private Pane pane;
    @FXML
    private Button logInButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeLogInButton();
    }

    private void placeLogInButton(){
        logInButton.setText("log in");
        logInButton.layoutXProperty().bind(pane.widthProperty().divide(10));
        logInButton.setOnAction( e -> getGuiClient().logIn() );
    }
/*
    public void successfullyLoggedIn(){
        getLoader().buildLobbyStage();
    }
*/
}
