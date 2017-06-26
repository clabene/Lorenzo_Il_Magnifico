package userInterface.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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

    private CheckBox woodAndStones = new CheckBox("1 wood + 1 stone");
    private CheckBox slaves = new CheckBox("2 slaves");
    private CheckBox money = new CheckBox("2 coins");
    private CheckBox militaryPoints = new CheckBox("2 military points");
    private CheckBox faithPoints = new CheckBox("1 faith point");

    private Stage stage;

    int numberOfFavours = 1;
    int i = 0;

    private Gainable[] favours = new Gainable[numberOfFavours];

    public void setNumberOfFavours(int numberOfFavours) {
        this.numberOfFavours = numberOfFavours;
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
        placeFavour(slaves, new Slave(3));
        placeFavour(money, new Money(3));
        placeFavour(militaryPoints, new MilitaryPointsTrack(2));
        placeFavour(faithPoints, new FaithPointsTrack(1));

    }

    private void placeFavour(CheckBox favour, Gainable gainable){
        favour.setLayoutX(100);
        favour.setOnAction( e -> {
            i++;
            favour.setMouseTransparent(true);
            favour.setVisible(false);
            favours[i-1] = gainable;
            if(i >= numberOfFavours) {
                ((MainViewController) getGuiClient().getController()).setFavours(favours);
                ((MainViewController) getGuiClient().getController()).setCanSend(true);
                stage.close();
            }
        });
        pane.getChildren().add(favour);

    }

    public Gainable[] getFavours() {
        return favours;
    }
}
