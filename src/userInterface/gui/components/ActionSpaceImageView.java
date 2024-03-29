package userInterface.gui.components;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import logic.board.Color;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IBM on 19/06/2017.
 */
public class ActionSpaceImageView extends ImageView {

    private ArrayList<FamilyMemberImageView> familyMembers = new ArrayList<>();
    private String actionSpaceId;


    //private BooleanProperty selected = new SimpleBooleanProperty(false);

    public ActionSpaceImageView(String actionSpaceId, String fxmlUrl) {
        super(fxmlUrl);

        this.actionSpaceId = actionSpaceId;
        //setOnMouseClicked( e -> {
        //    selected.set(true);
        //} );

    }
    //public BooleanProperty selectedProperty(){
    //    return selected;
    //}

    public ArrayList<FamilyMemberImageView> getFamilyMembers(){
        return familyMembers;
    }

    public int getNumberOfFamilyMembers() {
        return familyMembers.size();
    }

    public String getActionSpaceId() {
        return actionSpaceId;
    }

    public FamilyMemberImageView addFamilyMemberImage(String playerId, Color diceColor, Integer value) {
        FamilyMemberImageView familyMemberView;

        if (diceColor != null) familyMemberView = new FamilyMemberImageView(playerId, diceColor, value);
        else familyMemberView = new FamilyMemberImageView(playerId, value);

        familyMemberView.setFitHeight(45);
        familyMemberView.setFitWidth(45);

        familyMembers.add(familyMemberView);

        familyMemberView.xProperty().bind(xProperty()
                .add(familyMemberView.fitWidthProperty()
                        .multiply(familyMembers.size() - 1).add(10)
                ).add(15));
        familyMemberView.yProperty().bind(yProperty()
                .add(fitHeightProperty().subtract(
                        familyMemberView.fitHeightProperty()).divide(2)
                ));
        return familyMemberView;
    }

    public ArrayList<Node> getComponents(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(this);
        for(FamilyMemberImageView tmp : familyMembers)
            nodes.addAll(Arrays.asList(tmp.getComponents()));

        return nodes;
    }




}
