package userInterface.gui.components;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


import java.lang.annotation.Target;
import java.util.*;

/**
 * Created by IBM on 19/06/2017.
 */
public class BoardView {

    private HashMap<String, ActionSpaceImageView> actionSpaces = new HashMap<>();

    private TowerView[] towers = new TowerView[4];

    private ImageView[] diceView = new ImageView[3];
    private Label[] diceLabel = new Label[3];

    private BooleanProperty actionSpaceSelected = new SimpleBooleanProperty(false);
    private String selectedId;

    private ImageView[] tasselsView = new ImageView[3];

    public BoardView() {
        initializeActionSpaces();
        placeTowerActionSpaces();
        placeCouncilActionSpace();
        placeActivationActionSpaces();
        placeMarketActionSpaces();
        initializeTasselsView();
        inizializeDiceView();

        setActionSpacesSelectable();
    }

    public void initializeTasselsView(){

        tasselsView[0] = new ImageView();
        tasselsView[0].setFitHeight(120);
        tasselsView[0].setFitWidth(80);
        tasselsView[0].xProperty().bind(getXPosition());
        tasselsView[0].yProperty().bind(getYPosition().add(getHeightProperty()).add(10).multiply(2.3).add(150));
        //tasselsView[0].setX(300);
        //tasselsView[0].setY(300);

        tasselsView[1] = new ImageView();
        tasselsView[1].setFitHeight(120);
        tasselsView[1].setFitWidth(80);
        tasselsView[1].xProperty().bind(tasselsView[0].xProperty().add(tasselsView[0].fitWidthProperty()).add(10));
        tasselsView[1].yProperty().bind(tasselsView[0].yProperty().add(10));

        tasselsView[2] = new ImageView();
        tasselsView[2].setFitHeight(120);
        tasselsView[2].setFitWidth(80);
        tasselsView[2].xProperty().bind(tasselsView[1].xProperty().add(tasselsView[1].fitWidthProperty().add(10)));
        tasselsView[2].yProperty().bind(tasselsView[0].yProperty());
    }

    public void setImages(String a, String b, String c){
        tasselsView[0].setImage(new Image("userInterface/gui/images/tassels/"+a+".png"));
        tasselsView[1].setImage(new Image("userInterface/gui/images/tassels/"+b+".png"));
        tasselsView[2].setImage(new Image("userInterface/gui/images/tassels/"+c+".png"));
    }

    public void inizializeDiceView(){
        diceView[0] = new ImageView();
        diceView[0].setFitHeight(30);
        diceView[0].setFitWidth(30);
        diceView[0].xProperty().bind(tasselsView[2].xProperty().add(tasselsView[2].getFitWidth()+10));
        diceView[0].yProperty().bind(tasselsView[0].yProperty());

        diceView[1] = new ImageView();
        diceView[1].setFitHeight(30);
        diceView[1].setFitWidth(30);
        diceView[1].xProperty().bind(diceView[0].xProperty());
        diceView[1].yProperty().bind(diceView[0].yProperty().add(diceView[0].getFitWidth()+5));

        diceView[2] = new ImageView();
        diceView[2].setFitHeight(30);
        diceView[2].setFitWidth(30);
        diceView[2].xProperty().bind(diceView[1].xProperty());
        diceView[2].yProperty().bind(diceView[1].yProperty().add(diceView[0].getFitWidth()+5));

        diceLabel[0] = new Label();
        diceLabel[1] = new Label();
        diceLabel[2] = new Label();

        diceLabel[0].layoutXProperty().bind(diceView[0].xProperty().add(diceView[0].fitWidthProperty().divide(2).subtract(diceLabel[0].widthProperty().divide(2))));
        diceLabel[0].layoutYProperty().bind(diceView[0].yProperty().add(diceView[0].fitHeightProperty().divide(2).subtract(diceLabel[0].heightProperty().divide(2))));
        diceLabel[1].layoutXProperty().bind(diceView[1].xProperty().add(diceView[1].fitWidthProperty().divide(2).subtract(diceLabel[1].widthProperty().divide(2))));
        diceLabel[1].layoutYProperty().bind(diceView[1].yProperty().add(diceView[1].fitHeightProperty().divide(2).subtract(diceLabel[1].heightProperty().divide(2))));
        diceLabel[2].layoutXProperty().bind(diceView[2].xProperty().add(diceView[2].fitWidthProperty().divide(2).subtract(diceLabel[2].widthProperty().divide(2))));
        diceLabel[2].layoutYProperty().bind(diceView[2].yProperty().add(diceView[2].fitHeightProperty().divide(2).subtract(diceLabel[2].heightProperty().divide(2))));

        diceView[0].setImage(new Image("userInterface/gui/images/dice/nero.png"));
        diceView[1].setImage(new Image("userInterface/gui/images/dice/rosso.png"));
        diceView[2].setImage(new Image("userInterface/gui/images/dice/bianco.png"));
        diceLabel[0].setTextFill(javafx.scene.paint.Color.WHITE);
        diceLabel[0].setFont(Font.font("Garamond", 15));
        diceLabel[0].setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), new CornerRadii(100), null)));

        diceLabel[1].setTextFill(javafx.scene.paint.Color.WHITE);
        diceLabel[1].setFont(Font.font("Garamond", 15));
        diceLabel[1].setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), new CornerRadii(100), null)));

        diceLabel[2].setTextFill(javafx.scene.paint.Color.WHITE);
        diceLabel[2].setFont(Font.font("Garamond", 15));
        diceLabel[2].setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), new CornerRadii(100), null)));

    }

    public void setDice(int a, int b, int c){


        diceLabel[0].setText(String.valueOf(a));

        diceLabel[1].setText(String.valueOf(b));

        diceLabel[2].setText(String.valueOf(c));




    }


    private void initializeActionSpaces(){
        //todo
        actionSpaces.put("TL4", new TowerActionSpaceImageView("TL4", "userInterface/gui/images/actionSpaces/actionSpace_verde7.png", "Araldo"));
        actionSpaces.put("TL3", new TowerActionSpaceImageView("TL3", "userInterface/gui/images/actionSpaces/actionSpace_verde5.png", "Araldo"));
        actionSpaces.put("TL2", new TowerActionSpaceImageView("TL2", "userInterface/gui/images/actionSpaces/actionSpace_verde3.png", "Araldo"));
        actionSpaces.put("TL1", new TowerActionSpaceImageView("TL1", "userInterface/gui/images/actionSpaces/actionSpace_verde1.png", "Araldo"));

        actionSpaces.put("TP4", new TowerActionSpaceImageView("TP4", "userInterface/gui/images/actionSpaces/actionSpace_blu7.png", "Araldo"));
        actionSpaces.put("TP3", new TowerActionSpaceImageView("TP3", "userInterface/gui/images/actionSpaces/actionSpace_blu5.png", "Araldo"));
        actionSpaces.put("TP2", new TowerActionSpaceImageView("TP2", "userInterface/gui/images/actionSpaces/actionSpace_blu3.png", "Araldo"));
        actionSpaces.put("TP1", new TowerActionSpaceImageView("TP1", "userInterface/gui/images/actionSpaces/actionSpace_blu1.png", "Araldo"));

        actionSpaces.put("TB4", new TowerActionSpaceImageView("TB4", "userInterface/gui/images/actionSpaces/actionSpace_giallo7.png", "Araldo"));
        actionSpaces.put("TB3", new TowerActionSpaceImageView("TB3", "userInterface/gui/images/actionSpaces/actionSpace_giallo5.png", "Araldo"));
        actionSpaces.put("TB2", new TowerActionSpaceImageView("TB2", "userInterface/gui/images/actionSpaces/actionSpace_giallo3.png", "Araldo"));
        actionSpaces.put("TB1", new TowerActionSpaceImageView("TB1", "userInterface/gui/images/actionSpaces/actionSpace_giallo1.png", "Araldo"));

        actionSpaces.put("TV4", new TowerActionSpaceImageView("TV4", "userInterface/gui/images/actionSpaces/actionSpace_viola7.png", "Araldo"));
        actionSpaces.put("TV3", new TowerActionSpaceImageView("TV3", "userInterface/gui/images/actionSpaces/actionSpace_viola5.png", "Araldo"));
        actionSpaces.put("TV2", new TowerActionSpaceImageView("TV2", "userInterface/gui/images/actionSpaces/actionSpace_viola3.png", "Araldo"));
        actionSpaces.put("TV1", new TowerActionSpaceImageView("TV1", "userInterface/gui/images/actionSpaces/actionSpace_viola1.png", "Araldo"));

        actionSpaces.put("C", new ActionSpaceImageView("C", "userInterface/gui/images/actionSpaces/actionSpace_consiglio1.png"));

        actionSpaces.put("AP1", new ActionSpaceImageView("AP1", "userInterface/gui/images/actionSpaces/actionSpace_produzione1.png"));
        actionSpaces.put("AP2", new ActionSpaceImageView("AP2", "userInterface/gui/images/actionSpaces/actionSpace_produzione.png"));
        actionSpaces.put("AH1", new ActionSpaceImageView("AH1", "userInterface/gui/images/actionSpaces/actionSpace_raccolto1.png"));
        actionSpaces.put("AH2", new ActionSpaceImageView("AH2", "userInterface/gui/images/actionSpaces/actionSpace_raccolto.png"));

        actionSpaces.put("M1", new ActionSpaceImageView("M1", "userInterface/gui/images/actionSpaces/actionSpace_mercato1.png"));
        actionSpaces.put("M2", new ActionSpaceImageView("M2", "userInterface/gui/images/actionSpaces/actionSpace_mercato2.png"));
        actionSpaces.put("M3", new ActionSpaceImageView("M3", "userInterface/gui/images/actionSpaces/actionSpace_mercato3.png"));
        actionSpaces.put("M4", new ActionSpaceImageView("M4", "userInterface/gui/images/actionSpaces/actionSpace_mercato4.png"));

    }

    private ArrayList<ActionSpaceImageView> getActionSpacesFromInitials(String initials){
        ArrayList<ActionSpaceImageView> toReturn = new ArrayList<>();
        for(ActionSpaceImageView tmp : actionSpaces.values())
            if (tmp.getActionSpaceId().startsWith(initials))
                toReturn.add(tmp);

        Collections.sort(toReturn, new Comparator<ActionSpaceImageView>() {
            @Override
            public int compare(ActionSpaceImageView o1, ActionSpaceImageView o2) {
                return o1.getActionSpaceId().compareTo(o2.getActionSpaceId());
            }
        });
        return toReturn;
    }
    private void placeTowerActionSpaces(){
        towers[0] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TL").toArray(new TowerActionSpaceImageView[4]));
        //towers[0].getXPositionProperty().set(0);
        //towers[0].getYPositionProperty().set(0);

        towers[1] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TP").toArray(new TowerActionSpaceImageView[4]));
        towers[1].getXPositionProperty().bind(towers[0].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(25));
        towers[1].getYPositionProperty().bind(towers[0].getYPositionProperty());

        towers[2] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TB").toArray(new TowerActionSpaceImageView[4]));
        towers[2].getXPositionProperty().bind(towers[1].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(25));
        towers[2].getYPositionProperty().bind(towers[0].getYPositionProperty());

        towers[3] = new TowerView((TowerActionSpaceImageView[]) getActionSpacesFromInitials("TV").toArray(new TowerActionSpaceImageView[4]));
        towers[3].getXPositionProperty().bind(towers[2].getXPositionProperty().add(TowerActionSpaceImageView.getLeftOffset()).add(actionSpaces.get("TV1").fitWidthProperty()).add(25));
        towers[3].getYPositionProperty().bind(towers[0].getYPositionProperty());
    }
    private void placeCouncilActionSpace(){
        ActionSpaceImageView councilActionSpace = actionSpaces.get("C");
        councilActionSpace.xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.3)));
        councilActionSpace.yProperty().bind(towers[0].getYPositionProperty());

        //todo delete:
        councilActionSpace.setFitWidth(350);
        councilActionSpace.setFitHeight(150);
    }
    private void placeActivationActionSpaces(){
        ActionSpaceImageView harvestShort = actionSpaces.get("AH1");
        ActionSpaceImageView harvestLong = actionSpaces.get("AH2");
        ActionSpaceImageView productionShort = actionSpaces.get("AP1");
        ActionSpaceImageView productionLong = actionSpaces.get("AP2");

        harvestShort.xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.3)));
        harvestShort.yProperty().bind(towers[0].getYPositionProperty().add(150));
        harvestLong.xProperty().bind(harvestShort.xProperty().add(harvestShort.fitWidthProperty()).add(10));
        harvestLong.yProperty().bind(harvestShort.yProperty());

        productionShort.xProperty().bind(harvestShort.xProperty());
        productionShort.yProperty().bind(harvestShort.yProperty().add(harvestShort.fitHeightProperty().add(10)));
        productionLong.xProperty().bind(productionShort.xProperty().add(productionShort.fitWidthProperty()).add(10));
        productionLong.yProperty().bind(productionShort.yProperty());

        //todo delete:
        harvestShort.setFitWidth(100);
        harvestShort.setFitHeight(100);
        harvestLong.setFitWidth(350);
        harvestLong.setFitHeight(100);
        productionShort.setFitWidth(100);
        productionShort.setFitHeight(100);
        productionLong.setFitWidth(250);
        productionLong.setFitHeight(100);

    }

    private void placeMarketActionSpaces(){
        ActionSpaceImageView[] markets = new ActionSpaceImageView[4];
        markets[0] = actionSpaces.get("M1");
        markets[1] = actionSpaces.get("M2");
        markets[2] = actionSpaces.get("M3");
        markets[3] = actionSpaces.get("M4");

        markets[0].xProperty().bind(towers[3].getXPositionProperty().add(towers[0].getXPositionProperty().divide(1.3)));
        markets[0].yProperty().bind(towers[0].getYPositionProperty().add(400));

        markets[1].xProperty().bind(markets[0].xProperty().add(markets[0].fitWidthProperty()).add(20));
        markets[1].yProperty().bind(markets[0].yProperty());

        markets[2].xProperty().bind(markets[1].xProperty().add(markets[1].fitWidthProperty()).add(20));
        markets[2].yProperty().bind(markets[0].yProperty());

        markets[3].xProperty().bind(markets[2].xProperty().add(markets[2].fitWidthProperty()).add(20));
        markets[3].yProperty().bind(markets[0].yProperty());

        //todo delete:
        markets[0].setFitWidth(100);
        markets[0].setFitHeight(100);
        markets[1].setFitWidth(100);
        markets[1].setFitHeight(100);
        markets[2].setFitWidth(100);
        markets[2].setFitHeight(100);
        markets[3].setFitWidth(100);
        markets[3].setFitHeight(100);
    }



    public ArrayList<Node> getComponents(){
        ArrayList<Node> toReturn = new ArrayList<>();
        for(ActionSpaceImageView tmp : actionSpaces.values())
            toReturn.addAll(tmp.getComponents());
        for(int i = 0; i < 3; i++) {
            toReturn.add(tasselsView[i]);
            toReturn.add(diceView[i]);
            toReturn.add(diceLabel[i]);
        }
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
