package logic.board;


import logic.actionSpaces.*;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 09/05/2017.
 */
public class Board {

    public Board(){
        setHashMap();
    }


    //todo exception


    private HashMap<String, ActionSpace> actionSpaceHashMap = new HashMap<>();

    /**
     * first letter: T = tower ; C = council; A = activation; M = market.
     * second letter: L = land; P = person; B = building; V = venture; P = production; H = harvest.
     *
     */

    private void setHashMap(){

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

    public ArrayList<String> getTurnOrder(){
        CouncilActionSpace a = new CouncilActionSpace();
        a = (CouncilActionSpace) actionSpaceHashMap.get("C");
        return a.getFamilyMemberArrayList();
    }

    public HashMap<String, ActionSpace> getHashMap(){
        return actionSpaceHashMap;
    }
}
