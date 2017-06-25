package userInterface.gui.components;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.scene.Node;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by IBM on 19/06/2017.
 */
public class BoardView {

    private HashMap<String, ActionSpaceImageView> actionSpaces = new HashMap<>();

    private TowerView[] towers = new TowerView[4];

    private BooleanProperty actionSpaceSelected = new SimpleBooleanProperty(false);
    private String selectedId;

    public BoardView() {
        initializeActionSpaces();
        placeTowerActionSpaces();
        placeCouncilActionSpace();
        placeActivationActionSpaces();
        placeMarketActionSpaces();

        setActionSpacesSelectable();
    }

    private void initializeActionSpaces(){
        //todo
        actionSpaces.put("TL4", new TowerActionSpaceImageView("TL4", "userInterface/gui/images/actionSpaces/actionSpace_verde.png", "Araldo"));
        actionSpaces.put("TL3", new TowerActionSpaceImageView("TL3", "userInterface/gui/images/actionSpaces/actionSpace_verde.png", "Araldo"));
        actionSpaces.put("TL2", new TowerActionSpaceImageView("TL2", "userInterface/gui/images/actionSpaces/actionSpace_verde.png", "Araldo"));
        actionSpaces.put("TL1", new TowerActionSpaceImageView("TL1", "userInterface/gui/images/actionSpaces/actionSpace_verde.png", "Araldo"));

        actionSpaces.put("TP4", new TowerActionSpaceImageView("TP4", "userInterface/gui/images/actionSpaces/actionSpace_blu.png", "Araldo"));
        actionSpaces.put("TP3", new TowerActionSpaceImageView("TP3", "userInterface/gui/images/actionSpaces/actionSpace_blu.png", "Araldo"));
        actionSpaces.put("TP2", new TowerActionSpaceImageView("TP2", "userInterface/gui/images/actionSpaces/actionSpace_blu.png", "Araldo"));
        actionSpaces.put("TP1", new TowerActionSpaceImageView("TP1", "userInterface/gui/images/actionSpaces/actionSpace_blu.png", "Araldo"));

        actionSpaces.put("TB4", new TowerActionSpaceImageView("TB4", "userInterface/gui/images/actionSpaces/actionSpace_giallo.png", "Araldo"));
        actionSpaces.put("TB3", new TowerActionSpaceImageView("TB3", "userInterface/gui/images/actionSpaces/actionSpace_giallo.png", "Araldo"));
        actionSpaces.put("TB2", new TowerActionSpaceImageView("TB2", "userInterface/gui/images/actionSpaces/actionSpace_giallo.png", "Araldo"));
        actionSpaces.put("TB1", new TowerActionSpaceImageView("TB1", "userInterface/gui/images/actionSpaces/actionSpace_giallo.png", "Araldo"));

        actionSpaces.put("TV4", new TowerActionSpaceImageView("TV4", "userInterface/gui/images/actionSpaces/actionSpace_viola.png", "Araldo"));
        actionSpaces.put("TV3", new TowerActionSpaceImageView("TV3", "userInterface/gui/images/actionSpaces/actionSpace_viola.png", "Araldo"));
        actionSpaces.put("TV2", new TowerActionSpaceImageView("TV2", "userInterface/gui/images/actionSpaces/actionSpace_viola.png", "Araldo"));
        actionSpaces.put("TV1", new TowerActionSpaceImageView("TV1", "userInterface/gui/images/actionSpaces/actionSpace_viola.png", "Araldo"));

        actionSpaces.put("C", new ActionSpaceImageView("C", "userInterface/gui/images/actionSpaces/actionSpace_consiglio.png"));

        actionSpaces.put("AP1", new ActionSpaceImageView("AP1", "userInterface/gui/images/actionSpaces/actionSpace_produzione.png"));
        actionSpaces.put("AP2", new ActionSpaceImageView("AP2", "userInterface/gui/images/actionSpaces/actionSpace_produzione.png"));
        actionSpaces.put("AH1", new ActionSpaceImageView("AH1", "userInterface/gui/images/actionSpaces/actionSpace_raccolto.png"));
        actionSpaces.put("AH2", new ActionSpaceImageView("AH2", "userInterface/gui/images/actionSpaces/actionSpace_raccolto.png"));

        actionSpaces.put("M1", new ActionSpaceImageView("M1", "userInterface/gui/images/actionSpaces/actionSpace_mercato.png"));
        actionSpaces.put("M2", new ActionSpaceImageView("M2", "userInterface/gui/images/actionSpaces/actionSpace_mercato.png"));
        actionSpaces.put("M3", new ActionSpaceImageView("M3", "userInterface/gui/images/actionSpaces/actionSpace_mercato.png"));
        actionSpaces.put("M4", new ActionSpaceImageView("M4", "userInterface/gui/images/actionSpaces/actionSpace_mercato.png"));

    }

    private ArrayList<ActionSpaceImageView> getActionSpacesFromInitials(String initials){
        ArrayList<ActionSpaceImageView> toReturn = new ArrayList<>();
        for(ActionSpaceImageView tmp : actionSpaces.values())
            if (tmp.getActionSpaceId().startsWith(initials))
                toReturn.add(tmp);
        return toReturn;
    }
    private void placeTowerActionSpaces(){
        towers[0] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TL").toArray(new TowerActionSpaceImageView[4]));
        //towers[0].getXPositionProperty().set(0);
        //towers[0].getYPositionProperty().set(0);

        towers[1] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TP").toArray(new TowerActionSpaceImageView[4]));
        towers[1].getXPositionProperty().bind(towers[0].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(15));
        towers[1].getYPositionProperty().bind(towers[0].getYPositionProperty());

        towers[2] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TB").toArray(new TowerActionSpaceImageView[4]));
        towers[2].getXPositionProperty().bind(towers[1].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(15));
        towers[2].getYPositionProperty().bind(towers[0].getYPositionProperty());

        towers[3] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TV").toArray(new TowerActionSpaceImageView[4]));
        towers[3].getXPositionProperty().bind(towers[2].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(15));
        towers[3].getYPositionProperty().bind(towers[0].getYPositionProperty());
    }
    private void placeCouncilActionSpace(){
        ActionSpaceImageView councilActionSpace = actionSpaces.get("C");
        councilActionSpace.xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.6)));
        councilActionSpace.yProperty().bind(towers[0].getYPositionProperty());

        //todo delete:
        councilActionSpace.setFitWidth(250);
        councilActionSpace.setFitHeight(100);
    }
    private void placeActivationActionSpaces(){
        ActionSpaceImageView harvestShort = actionSpaces.get("AH1");
        ActionSpaceImageView harvestLong = actionSpaces.get("AH2");
        ActionSpaceImageView productionShort = actionSpaces.get("AP1");
        ActionSpaceImageView productionLong = actionSpaces.get("AP2");

        harvestShort.xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.6)));
        harvestShort.yProperty().bind(towers[0].getYPositionProperty().add(150));
        harvestLong.xProperty().bind(harvestShort.xProperty().add(harvestShort.fitWidthProperty()).add(10));
        harvestLong.yProperty().bind(harvestShort.yProperty());

        productionShort.xProperty().bind(harvestShort.xProperty());
        productionShort.yProperty().bind(harvestShort.yProperty().add(harvestShort.fitHeightProperty().add(10)));
        productionLong.xProperty().bind(productionShort.xProperty().add(productionShort.fitWidthProperty()).add(10));
        productionLong.yProperty().bind(productionShort.yProperty());

        //todo delete:
        harvestShort.setFitWidth(70);
        harvestShort.setFitHeight(70);
        harvestLong.setFitWidth(250);
        harvestLong.setFitHeight(70);
        productionShort.setFitWidth(70);
        productionShort.setFitHeight(70);
        productionLong.setFitWidth(250);
        productionLong.setFitHeight(70);

    }

    private void placeMarketActionSpaces(){
        ActionSpaceImageView[] markets = new ActionSpaceImageView[4];
        markets[0] = actionSpaces.get("M1");
        markets[1] = actionSpaces.get("M2");
        markets[2] = actionSpaces.get("M3");
        markets[3] = actionSpaces.get("M4");

        markets[0].xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.6)));
        markets[0].yProperty().bind(towers[0].getYPositionProperty().add(300));

        markets[1].xProperty().bind(markets[0].xProperty().add(markets[0].fitWidthProperty()).add(20));
        markets[1].yProperty().bind(markets[0].yProperty());

        markets[2].xProperty().bind(markets[1].xProperty().add(markets[1].fitWidthProperty()).add(20));
        markets[2].yProperty().bind(markets[0].yProperty());

        markets[3].xProperty().bind(markets[2].xProperty().add(markets[2].fitWidthProperty()).add(20));
        markets[3].yProperty().bind(markets[0].yProperty());

        //todo delete:
        markets[0].setFitWidth(70);
        markets[0].setFitHeight(70);
        markets[1].setFitWidth(70);
        markets[1].setFitHeight(70);
        markets[2].setFitWidth(70);
        markets[2].setFitHeight(70);
        markets[3].setFitWidth(70);
        markets[3].setFitHeight(70);
    }



    public HashSet<Node> getComponents(){
        HashSet<Node> toReturn = new HashSet<>();
        for(ActionSpaceImageView tmp : actionSpaces.values())
            toReturn.addAll(tmp.getComponents());
        return toReturn;
    }
    public DoubleBinding getXPosition() {
        return towers[0].getXPositionProperty().subtract(TowerActionSpaceImageView.getLeftOffset());
    }

    public DoubleBinding getYPosition() {
        return towers[0].getYPositionProperty().subtract(TowerActionSpaceImageView.getSuperiorOffset());
    }
    public DoubleBinding getWidthProperty() {
        return actionSpaces.get("M4").xProperty()
                .add(actionSpaces.get("M4").fitWidthProperty())
                .subtract(getXPosition());
    }

    public DoubleBinding getHeightProperty() {
        return actionSpaces.get("TL4").yProperty()
                .add(actionSpaces.get("TL4").fitHeightProperty())
                .add(TowerActionSpaceImageView.getSuperiorOffset())
                .subtract(getYPosition());
    }
    public void bindXPosition(DoubleBinding toBindXTo){
        towers[0].getXPositionProperty().bind(toBindXTo.add(TowerActionSpaceImageView.getLeftOffset()));
    }
    public void bindYPosition(DoubleBinding toBindYTo){
        towers[0].getYPositionProperty().bind(toBindYTo.add(TowerActionSpaceImageView.getSuperiorOffset()));
    }

    public ActionSpaceImageView getActionSpaceFromId(String actionSpaceId) {
        return actionSpaces.get(actionSpaceId);
    }



    private void setActionSpacesSelectable(){
        for(ActionSpaceImageView tmp : actionSpaces.values())
            tmp.setOnMouseClicked( e -> {
                selectedId = tmp.getActionSpaceId();
                actionSpaceSelected.set(true);
            });
    }
    public BooleanProperty actionSpaceSelectedProperty() {
        return actionSpaceSelected;
    }
    public String getSelectedId() {
        return selectedId;
    }

    public Collection<ActionSpaceImageView> getActionSpaces(){
        return actionSpaces.values();
    }


}
