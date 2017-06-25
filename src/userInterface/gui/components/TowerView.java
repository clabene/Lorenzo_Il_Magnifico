package userInterface.gui.components;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.Node;


import java.util.*;

/**
 * Created by IBM on 19/06/2017.
 */
public class TowerView {

    private TowerActionSpaceImageView[] towerActionSpaces;
    private SimpleDoubleProperty xPosition = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty yPosition = new SimpleDoubleProperty(0);

    public TowerView(double xPosition, double yPosition, TowerActionSpaceImageView ... towerActionSpaces){
        //super(towerActionSpaces);
        this.towerActionSpaces = towerActionSpaces;
        this.xPosition.set(xPosition);
        this.yPosition.set(yPosition);
        placeTowerActionSpaces();
    }

    public TowerView(TowerActionSpaceImageView ... towerActionSpaces){
        this(0, 0, towerActionSpaces);
    }

    private void placeTowerActionSpaces(){
        int i = 0;
        for(int j = 3; j >= 0; j--){
        /*for(TowerActionSpaceImageView tmp : towerActionSpaces){
            tmp.setFitWidth(50);
            tmp.setFitHeight(50);
            tmp.xProperty().bind(xPosition);
            tmp.yProperty().bind(yPosition.add(tmp.getHeightProperty().add(10).multiply(i)));*/
            towerActionSpaces[j].setFitWidth(100);
            towerActionSpaces[j].setFitHeight(100);
            towerActionSpaces[j].xProperty().bind(xPosition);
            towerActionSpaces[j].yProperty().bind(yPosition.add(towerActionSpaces[j].getHeightProperty().add(10).multiply(i)));

            i++;
        }
    }

    public SimpleDoubleProperty getXPositionProperty() {
        return xPosition;
    }
    public SimpleDoubleProperty getYPositionProperty() {
        return yPosition;
    }

    public ArrayList<Node> getComponents() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(Arrays.asList(towerActionSpaces));
        for(TowerActionSpaceImageView tmp : towerActionSpaces)
            nodes.addAll(tmp.getComponents());
        return nodes;
    }

}
