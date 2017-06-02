package board;

import actionSpaces.ActionSpace;
import pointsTracks.MilitaryPointsTrack;
import resources.*;
import actionSpaces.MarketActionSpace;




/**
 * Created by Pinos on 25/05/2017.
 */
public class MarketArea implements Area {
    private MarketActionSpace[] spaces = {new MarketActionSpace(new Money(5)), new  MarketActionSpace(new Slave(5)),
            new MarketActionSpace(new Money(2), new MilitaryPointsTrack(3)), new MarketActionSpace(new CouncilFavour(2)) };


    public MarketActionSpace[] getMarketActionSpaces(){
        return this.spaces;
    }

    @Override
    public void show() {
        int i = 0;
        for( MarketActionSpace tmp : spaces){
            i++;
            System.out.println(i+ " "+ "MarketActionSpace"+ tmp.toString() + "\n");

        }

    }

    @Override
    public ActionSpace getActionSpace(int index) {
        return spaces[index];
    }

    @Override
    public String toString(){
        return "Market Area";
    }


}
