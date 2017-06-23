package userInterface.gui.component;


import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import logic.board.Color;
import userInterface.PlayerColor;

/**
 * Created by IBM on 19/06/2017.
 */
public class FamilyMemberImageView extends ImageView {

    private Label valueLabel = new Label();
    private int value;
    private Color diceColor;
    public FamilyMemberImageView(PlayerColor playerColor, Color diceColor, Integer value){
        //todo
        //super("userInterface/gui/images/"+playerColor.toString().toLowerCase()+"_family_member_"+diceColor.toString().toLowerCase()+".png"); //todo
        super("userInterface/gui/images/cancellami3.jpg");
        this.value = value;
        this.diceColor = diceColor;
        placeValueLabel(value.toString());
    }

    public Color getDiceColor() {
        return diceColor;
    }

    public int getValue() {
        return value;
    }

    private void placeValueLabel(String value){
        valueLabel.layoutXProperty().bind(xProperty().add(fitWidthProperty().divide(2)));
        valueLabel.layoutYProperty().bind(yProperty().add(fitHeightProperty().divide(2)).subtract(valueLabel.heightProperty().divide(2)));
        valueLabel.setText(value);
        valueLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        valueLabel.setFont(Font.font("Garamond", 20));

        valueLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), new CornerRadii(100), null)));
    }

    public void setValue(String value) {
         valueLabel.setText(" "+value+" ");
    }


    public StringProperty getValueProperty(){
        return valueLabel.textProperty();
    }

    public Node[] getComponents(){
        Node[] nodes = new Node[2];
        nodes[0] = this;
        nodes[1] = valueLabel;
        return nodes;
    }

}
