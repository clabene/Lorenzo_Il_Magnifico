package logic.board;


import logic.actionSpaces.*;
import logic.cards.Card;
import logic.exceptions.ActionSpaceCoveredException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by IBM on 09/05/2017.
 */
public class Board {

    private final int NUMBER_OF_PLAYERS; //use LimitedInteger

    public Board(int NUMBER_OF_PLAYERS){
        this.NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS;
        setHashMap(NUMBER_OF_PLAYERS);
    }




    private ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];

    private HashMap<String, ActionSpace> actionSpaceHashMap = new HashMap<>();

    /**
     * first letter: T = tower ; C = council; A = activation; M = market.
     * second letter: L = land; P = person; B = building; V = venture; P = production; H = harvest.
     *
     */

    private void setHashMap(int numberOfPlayers){

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
        if(numberOfPlayers > 2) actionSpaceHashMap.put("AP2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION ));
        actionSpaceHashMap.put("AH1", new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST));
        if(numberOfPlayers > 2) actionSpaceHashMap.put("AH2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST ));
        actionSpaceHashMap.put("M1", new MarketActionSpace(new Money(5)));
        actionSpaceHashMap.put("M2", new  MarketActionSpace(new Slave(5)));
        if(numberOfPlayers > 3) actionSpaceHashMap.put("M3", new MarketActionSpace(new Money(2), new MilitaryPointsTrack(3)));
        if(numberOfPlayers > 3) actionSpaceHashMap.put("M4", new MarketActionSpace(new CouncilFavour(2)));
    }

    public ActionSpace tryToSelectActionSpace(String key) throws ActionSpaceCoveredException{
        ActionSpace actionSpace = actionSpaceHashMap.get(key);
        if(actionSpace.getCovered()) throw new ActionSpaceCoveredException();
        return actionSpace;
    }

    public ArrayList<String> getTurnOrder(){
        CouncilActionSpace a;
        a = (CouncilActionSpace) actionSpaceHashMap.get("C");
        return a.getFamilyMemberArrayList();
    }

    public HashMap<String, ActionSpace> getHashMap(){
        return actionSpaceHashMap;
    }

    public void setCardsOnBoard(Stack<Card> deck){
        int i = 0;
        for(ActionSpace tmp: actionSpaceHashMap.values()){
            ((TowerActionSpace) tmp).setCard(deck.pop());
            i++;
            if(i == 16)  break;
        }
    }

    public ExcommunicationTassel[] getTassels() {
        return tassels;
    }
}
