package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 25/06/2017.
 */
public class VaticanInspectionController extends Controller{

    @FXML
    private Pane pane;
    @FXML
    private Button excommunicationButton, supportButton;
    @FXML
    private Label label;

    private boolean notSupporting = true; //false -> excommunication, true -> support

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setLayoutX(20);
        label.setLayoutY(20);
        excommunicationButton.setLayoutX(100);
        supportButton.setLayoutX(250);
        supportButton.setLayoutY(100);
        excommunicationButton.setLayoutY(100);
    }

    public void setStage(Stage stage){
        supportButton.setOnAction( e -> {
            ((MainViewController)getGuiClient().getController()).setCanSendVatican(true);
            ((MainViewController)getGuiClient().getController()).setNotSupporting(false);
            notSupporting = false;
            stage.close();
        } );
        excommunicationButton.setOnAction( e -> {
            ((MainViewController)getGuiClient().getController()).setCanSendVatican(true);
            ((MainViewController)getGuiClient().getController()).setNotSupporting(true);
            notSupporting = true;
            stage.close();
        } );
    }

    public boolean getNotSupporting(){
        return notSupporting;
    }

}
