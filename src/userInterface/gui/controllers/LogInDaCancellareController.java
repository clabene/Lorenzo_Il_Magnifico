package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeLogInButton();
    //    placeImageView();
    }

    private void placeLogInButton(){
        logInButton.setText("log in");
        logInButton.layoutXProperty().bind(pane.widthProperty().divide(10));
        logInButton.setOnAction( e -> getGuiClient().logIn() );
    }
/*
    private void placeImageView(){
        image.setImage(new Image("userInterface/gui/lorenzo-magnifico.jpg"));
        image.setLayoutX(100);
    }
    */
/*
    public void successfullyLoggedIn(){
        getLoader().buildLobbyStage();
    }
*/
}
