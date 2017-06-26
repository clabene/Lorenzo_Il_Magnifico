package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class LogInController extends Controller {

    @FXML
    private Pane pane;
    @FXML
    private Button logInButton;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initilaizeImageV();
        placeLogInButton();

    //    placeImageView();
    }

    private void placeLogInButton(){
        logInButton.setText("log in");
        logInButton.layoutXProperty().bind(pane.widthProperty().divide(10));
        logInButton.setOnAction( e -> getGuiClient().logIn() );
    }

    public void initilaizeImageV(){

        pane.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        /*
        image = new ImageView();
        image.setImage(new Image("userInterface/images/gui/sfondo_firenze.jpg"));
        image.setX(pane.getLayoutX());
        image.setY(pane.getLayoutY());
        image.setFitWidth(pane.getPrefWidth());
        image.setFitHeight(pane.getPrefHeight());
        pane.getChildren().add(image);*/

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
