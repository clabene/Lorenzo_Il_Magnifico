package userInterface.gui.components;


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

/**
 * Created by IBM on 19/06/2017.
 */
public class FamilyMemberImageView extends ImageView {

    private Label valueLabel = new Label();
    private int value;
    private Color diceColor;
    private String playerId; //todo get PlayerColor form here

    public FamilyMemberImageView(String playerId, Color diceColor, Integer value) {

        //todo
        //super("userInterface/gui/images/"+playerColor.toString().toLowerCase()+"_family_member_"+diceColor.toString().toLowerCase()+".png"); //todo
        super("userInterface/gui/images/cancellami3.jpg");
        this.playerId = playerId;
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

    private void placeValueLabel(String value) {
        valueLabel.layoutXProperty().bind(xProperty().add(fitWidthProperty().divide(2).subtract(valueLabel.widthProperty().divide(2))));
        valueLabel.layoutYProperty().bind(yProperty().add(fitHeightProperty().divide(2).subtract(valueLabel.heightProperty().divide(2))));
        valueLabel.setText(value);
        valueLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        valueLabel.setFont(Font.font("Garamond", 15));

        valueLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), new CornerRadii(100), null)));
    }

    public void setValue(String value) {
         valueLabel.setText(" "+value+" ");
    }

    public void toHide(boolean toHide){
        for(Node tmp : getComponents()){
            tmp.setMouseTransparent(toHide);
            tmp.setVisible(!toHide);
        }
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
