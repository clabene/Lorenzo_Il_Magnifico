package userInterface.gui.component;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by IBM on 19/06/2017.
 */

public class LabeledImageView extends ImageView {
    private Label label = new Label();

    public LabeledImageView(String message, String imageUrl){
        super(imageUrl);
        placeLabel();
        label.setText(message);
    }

    public void setText(String text) {
        this.label.setText(text);
    }

    private void placeLabel(){
        label.layoutXProperty().bind(xProperty().add(fitWidthProperty()).add(5));
        label.layoutYProperty().bind(yProperty().add(fitHeightProperty().divide(2)));
    }

    public Node[] getComponents() {
        Node[] nodes = {this, label};
        return nodes;
    }
}
