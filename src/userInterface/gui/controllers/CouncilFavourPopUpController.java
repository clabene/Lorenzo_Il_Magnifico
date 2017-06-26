package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.interfaces.Gainable;
import logic.pointsTracks.FaithPointsTrack;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 25/06/2017.
 */
public class CouncilFavourPopUpController extends Controller {

    @FXML
    private Pane pane;

    private Label woodAndStones = new Label("1 wood + 1 stone");
    private Label slaves = new Label("2 slaves");
    private Label money = new Label("2 coins");
    private Label militaryPoints = new Label("2 military points");
    private Label faithPoints = new Label("1 faith point");

    private Stage stage;

    int numberOfFavours = 1;
    int i = 0;

    private Gainable[] favours;

    public void setNumberOfFavours(int numberOfFavours) {
        this.numberOfFavours = numberOfFavours;
        favours = new Gainable[numberOfFavours];
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        woodAndStones.setLayoutX(100); woodAndStones.setLayoutY(20);
        slaves.setLayoutX(100); slaves.setLayoutY(70);
        money.setLayoutX(100); money.setLayoutY(120);
        militaryPoints.setLayoutX(100); militaryPoints.setLayoutY(170);
        faithPoints.setLayoutX(100); faithPoints.setLayoutY(210);

        placeFavour(woodAndStones, new SetOfResources(new Wood(), new Stone()));
        placeFavour(slaves, new Slave(2));
        placeFavour(money, new Money(2));
        placeFavour(militaryPoints, new MilitaryPointsTrack(2));
        placeFavour(faithPoints, new FaithPointsTrack(1));

    }

    private void placeFavour(Label favour, Gainable gainable){
        favour.setOnMouseClicked( e -> {
            i++;
            favour.setMouseTransparent(true);
            favour.setVisible(false);
            favours[i-1] = gainable;
            if(i >= numberOfFavours) {
                ((MainViewController) getGuiClient().getController()).setFavours(favours);
                ((MainViewController) getGuiClient().getController()).setCanSendFavour(true);
                stage.close();
            }
        });
        pane.getChildren().add(favour);
    }




    public Gainable[] getFavours() {
        return favours;
    }
}
