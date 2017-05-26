package board;

import board.*;

/**
 * Created by IBM on 09/05/2017.
 */
public class Board {
    Area[] areas = {new TowerArea(), new MarketArea(), new CouncilArea(), new ChurchArea(), new ActivationArea()};

    public void show(){
        int i = 0;
        for(Area tmp: areas){
            i++;
            System.out.println(i+" "+ " Area: "+ tmp.toString()+ "\n");
        }
    }

    public Area getArea(int index){
        return areas[index];
    }

}
