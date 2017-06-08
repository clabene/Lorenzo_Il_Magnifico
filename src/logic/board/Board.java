package logic.board;


import logic.actionSpaces.*;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;

import java.util.HashMap;

/**
 * Created by IBM on 09/05/2017.
 */
public class Board {
    private Area[] areas = {new TowerArea(), new MarketArea(), new CouncilArea(), new ActivationArea(), new ChurchArea()};

    public void show(){
        int i = 0;
        for(Area tmp: areas) {
            i++;
            System.out.println(i+" "+ " Area: "+ tmp.toString()+ "\n");
        }
    }

    public CouncilArea getCouncilArea(){
        return (CouncilArea) this.areas[2];
    }

    public Area getArea(int index){
        return areas[index];
    }


    //todo exception
    public Area tryToSelectArea(Area area){
        if(area instanceof TowerArea) return this.areas[0];
        else if(area instanceof MarketArea) return this.areas[1];
        else if(area instanceof CouncilArea) return this.areas[2];
        else if(area instanceof ActivationArea) return this.areas[3];
        return null;
    }


    public TowerArea getTowerArea(){return (TowerArea) this.areas[0];}

    //-------------------------------------------------------------------

    private HashMap<String, ActionSpace> actionSpaceHashMap = new HashMap<>();

    public void setHashMap(){
        actionSpaceHashMap.put("TL1", new TowerActionSpace(1) );
        actionSpaceHashMap.put("TL2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TL3", new TowerActionSpace (5, new Wood(1)));
        actionSpaceHashMap.put("TL4", new TowerActionSpace (7, new Wood(2)));
        actionSpaceHashMap.put("TP1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TP2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TP3", new TowerActionSpace(5, new Stone(1)));
        actionSpaceHashMap.put("TP4", new TowerActionSpace(7, new Stone(2)));
        actionSpaceHashMap.put("TB1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TB2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TB3", new TowerActionSpace(5, new MilitaryPointsTrack(1)));
        actionSpaceHashMap.put("TB4", new TowerActionSpace(7, new MilitaryPointsTrack(2)));
        actionSpaceHashMap.put("TV1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TV2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TV3", new TowerActionSpace(5, new Money(1)));
        actionSpaceHashMap.put("TV4", new TowerActionSpace(7, new Money(2)));
        actionSpaceHashMap.put("C", new CouncilActionSpace());
        actionSpaceHashMap.put("AP1", new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION));
        actionSpaceHashMap.put("AP2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION ));
        actionSpaceHashMap.put("AH1", new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST));
        actionSpaceHashMap.put("AH2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST ));
        actionSpaceHashMap.put("M1", new MarketActionSpace(new Money(5)));
        actionSpaceHashMap.put("M2", new  MarketActionSpace(new Slave(5)));
        actionSpaceHashMap.put("M3", new MarketActionSpace(new Money(2), new MilitaryPointsTrack(3)));
        actionSpaceHashMap.put("M4", new MarketActionSpace(new CouncilFavour(2)));
    }

    //todo  exceptions
    public ActionSpace tryToSelectActionSpace(String key){
        ActionSpace actionSpace = actionSpaceHashMap.get(key);

        if(actionSpace.getCovered()) return null;

        return actionSpace;
    }


}
