package userInterface.gui.component;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Created by IBM on 19/06/2017.
 */
public class PopUp extends Stage {

    private Label label = new Label("");
    private ImageView imageView = new ImageView();
    private Button okButton = new Button("OK");
    private Pane pane = new Pane();

    public PopUp(String message, String imageUrl){
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        placeButton();
        placeLabel(message);
        placeImageView(imageUrl);

        setScene(new Scene(pane, 300, 300));
    }
    public PopUp(String message, Image image){
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        placeButton();
        placeLabel(message);
        placeImageView(image);

        setScene(new Scene(pane, 300, 300));
    }

    private void placeButton(){
        okButton.setLayoutX(pane.getWidth()+270);
        okButton.setLayoutY(pane.getHeight()+270);
        okButton.setOnAction( e -> close());
        pane.getChildren().add(okButton);
    }
    private void placeLabel(String message){
        label.setLayoutX(20);
        label.setLayoutY(5);
        label.setText(message);
        pane.getChildren().add(label);
    }
    private void placeImageView(String imageUrl){
        imageView.setLayoutY(30);
        imageView.setLayoutX(10);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(imageUrl));
        pane.getChildren().add(imageView);
    }
    private void placeImageView(Image image){
        imageView.setLayoutY(30);
        imageView.setLayoutX(10);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        pane.getChildren().add(imageView);
    }

}
