package userInterface.gui.controllers;

import javafx.scene.control.CheckBox;
import userInterface.gui.components.LabeledImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IBM on 25/06/2017.
 */
public class CouncilFavourPopUpController extends Controller {

    private CheckBox woodAndStones = new CheckBox("1 wood + 1 stone");
    private LabeledImageView slaves = new LabeledImageView("quantity 2", "");
    private LabeledImageView money = new LabeledImageView("quantity 2", "");
    private LabeledImageView militaryPoints = new LabeledImageView("quantity 2", "");
    private LabeledImageView faithPoints = new LabeledImageView("quantity 1", "");

    // todo make an image of a wood and a stone one next to the other
    //private LabeledImageView woodAndStones = new LabeledImageView("quantity 1", "");
/*
    private LabeledImageView slaves = new LabeledImageView("quantity 2", "");
    private LabeledImageView money = new LabeledImageView("quantity 2", "");
    private LabeledImageView militaryPoints = new LabeledImageView("quantity 2", "");
    private LabeledImageView faithPoints = new LabeledImageView("quantity 1", "");
*/

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //woodAndStones.setFitWidth(45); money.setFitHeight(45);
        slaves.setFitWidth(45); slaves.setFitHeight(45);
        militaryPoints.setFitWidth(45); militaryPoints.setFitHeight(45);
        faithPoints.setFitWidth(45); faithPoints.setFitHeight(45);


        woodAndStones.setLayoutX(100); woodAndStones.setLayoutX(20);
        slaves.setX(100); slaves.setY(70);
        money.setX(100); money.setY(120);
        militaryPoints.setX(100); militaryPoints.setY(170);
        faithPoints.setX(100); faithPoints.setY(210);




    }

}
