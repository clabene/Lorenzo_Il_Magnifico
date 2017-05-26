package board;

import actionSpaces.ActionSpace;
import actionSpaces.TowerActionSpace;
import pointsTrack.MilitaryPointsTrack;
import resources.*;
import utility.StaticVariables;


/**
 * Created by Pinos on 26/05/2017.
 */
public class TowerArea implements Area {
    private TowerActionSpace[] spaces = StaticVariables.TOWER_ACTION_SPACES;


    @Override
    public void show() {
        int i = 0;
        for(TowerActionSpace tmp: spaces){
                i++;
                System.out.println(i+" TowerActionSPace: "+ tmp.toString()+ "\n");


        }
    }

    @Override
    public ActionSpace getActionSpace(int index) {
        return spaces[index];
    }
}
