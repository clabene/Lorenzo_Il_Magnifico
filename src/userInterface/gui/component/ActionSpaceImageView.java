package userInterface.gui.component;

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


    public int getNumberOfFamilyMembers() {
        return familyMembers.size();
    }

    public String getActionSpaceId() {
        return actionSpaceId;
    }

    public FamilyMemberImageView addFamilyMemberImage(String playerId, Color diceColor, Integer value) {
        FamilyMemberImageView familyMemberView = new FamilyMemberImageView(playerId, diceColor, value);
        familyMemberView.setFitHeight(40);
        familyMemberView.setFitWidth(40);

        familyMembers.add(familyMemberView);

        familyMemberView.xProperty().bind(xProperty()
                .add(familyMemberView.fitWidthProperty()
                                .multiply(familyMembers.size()-1).add(10)
                ));
        familyMemberView.yProperty().bind(yProperty()
                .add(fitHeightProperty().subtract(
                        familyMemberView.fitHeightProperty()).divide(2)
                ));
        return familyMemberView;
    }
    public void addFamilyMemberImage(FamilyMemberImageView familyMember) {
        familyMember.setFitHeight(40);
        familyMember.setFitWidth(40);

        familyMembers.add(familyMember);

        familyMember.xProperty().bind(xProperty()
                .add(familyMember.fitWidthProperty()
                        .multiply(familyMembers.size()-1).add(10)
                ));
        familyMember.yProperty().bind(yProperty()
                .add(fitHeightProperty().subtract(
                        familyMember.fitHeightProperty()).divide(2)
                ));
    }

    public ArrayList<Node> getComponents(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(this);
        for(FamilyMemberImageView tmp : familyMembers)
            nodes.addAll(Arrays.asList(tmp.getComponents()));

        return nodes;
    }




}
