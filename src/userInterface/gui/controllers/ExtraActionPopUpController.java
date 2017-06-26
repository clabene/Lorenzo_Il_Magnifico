package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.CouncilActionSpace;
import logic.actionSpaces.MarketActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.interfaces.Gainable;
import logic.player.Player;
import logic.resources.Wood;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by IBM on 25/06/2017.
 */
public class ExtraActionPopUpController extends Controller {
    @FXML
    private Pane pane;

    private Stage stage;

    private ArrayList<ActionSpace> actionSpaces;
    private ActionSpace actionSpace = new MarketActionSpace(new Wood(0)); //default value

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setActionSpaces(ArrayList<ActionSpace> actionSpaces) {
        this.actionSpaces = actionSpaces;
        placeLabels(actionSpaces);
    }

    public ActionSpace getActionSpace() {
        return actionSpace;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void placeLabels( ArrayList<ActionSpace> actionSpaces ){
        int i = 0;
        int j = 0;
        for(ActionSpace tmp : actionSpaces) {
            Label label = new Label(((TowerActionSpace)tmp).getCard().getName()); //if were not tower action spaces, would not have got here
            label.setLayoutX(130+60*j);
            label.setLayoutY(20+30*i);
            label.setOnMouseClicked( e ->{
                ((MainViewController)getGuiClient().getController()).setActionSpace(actionSpace);
                ((MainViewController)getGuiClient().getController()).setCanSend(true);
                stage.close();
            } );
            if(i == 10){
                i = 0;
                j++;
            }
            i++;
            pane.getChildren().add(label);
        }
    }



}
