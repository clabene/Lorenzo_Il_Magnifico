package userInterface.gui.components;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import userInterface.gui.Loader;

import java.util.ArrayList;

/**
 * Created by IBM on 19/06/2017.
 */
public class TowerActionSpaceImageView extends ActionSpaceImageView {

    private ImageView card = new ImageView();
    private String cardName;

    public TowerActionSpaceImageView(String actionSpaceId, String imageUrl, String cardName){
        super(actionSpaceId, imageUrl);
        this.cardName = cardName;
        placeCard(cardName);
    }

    private void placeCard(String cardName) {
        card.xProperty().bind(xProperty()
                .subtract(card.fitWidthProperty()
                        .add(2)
                )
        );
        card.yProperty().bind(yProperty()
                .subtract(card.fitHeightProperty()
                        .divide(2)
                )
                .add(fitHeightProperty()
                        .divide(2)
                )
        );

        card.setFitWidth(90);
        card.setFitHeight(120);


        card.setImage(new Image("userInterface/gui/images/cards/"+cardName+".png"));
        card.setOnMouseClicked( e -> Loader.buildPopUp("Selected card", cardName, card.getImage()));

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardImage(String imageUrl){
        card.setMouseTransparent(false);
        card.setVisible(true);
        card.setImage(new Image(imageUrl));
    }
    public void setCardToTaken(){
        System.out.println(card);
        card.setMouseTransparent(true);
        card.setVisible(false);
    }


    public DoubleProperty getHeightProperty(){
        return card.fitHeightProperty();
    }

    public static double getLeftOffset(){
        return 75; //a card's width
    }

    public static double getSuperiorOffset(){
        return 25; //this is how much the card goes over the top of the action space
    }

    @Override
    public ArrayList<Node> getComponents() {
        ArrayList<Node> toReturn = new ArrayList<>();
        toReturn.addAll(super.getComponents());
        toReturn.add(card);
        return toReturn;
    }
}
