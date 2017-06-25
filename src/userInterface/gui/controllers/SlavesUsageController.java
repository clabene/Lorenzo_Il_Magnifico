package userInterface.gui.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 24/06/2017.
 */
public class SlavesUsageController extends Controller{

    @FXML
    private Label quantity;
    @FXML
    private ImageView minus;
    @FXML
    private ImageView plus;
    @FXML
    private Label label;
    @FXML
    private Button okButton;

    private IntegerProperty i = new SimpleIntegerProperty(1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setLayoutX(20);

        okButton.setLayoutX(350);
        okButton.setLayoutY(253);

        quantity.setText(" 1 ");
        quantity.textProperty().bind(i.asString());
        quantity.setLayoutX(170);
        quantity.setLayoutY(240);

        plus.setOnMouseClicked( e -> i.set(i.get()+1) );
        plus.setLayoutX(340);
        plus.setFitWidth(50);
        plus.setPreserveRatio(true);

        minus.setLayoutX(10);
        minus.setFitWidth(50);
        minus.setPreserveRatio(true);
        minus.setOnMouseClicked( e -> {
            if(i.get() != 0) i.set(i.get()-1);
        } );
    }

    public int getQuantity(){
        return i.get();
    }

    public void setStage(Stage stage){
        okButton.setOnAction( e -> {
            getGuiClient().useSlaves(i.get());
            stage.close();
        } );
    }


}
