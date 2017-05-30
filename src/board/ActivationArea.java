package board;

import actionSpaces.*;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ActivationArea implements Area {

    //private ActivationActionSpace firstProductionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION );
    //private ActivationActionSpace firstHarvestSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
    //private ActivationActionSpace secondProductionSpace = new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION );
    //private ActivationActionSpace secondHarvestSpace = new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST );

    private ActivationActionSpace[] spaces = {new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION) ,
            new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST),
            new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION ),
            new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST )};

    @Override
    public void show() {
        int i = 0;
        for( ActivationActionSpace tmp : spaces){
            i++;
            System.out.println(i+ " "+ "ActivationActionSpace"+ tmp.toString() + "\n");

        }
    }

    @Override
    public ActionSpace getActionSpace(int index) {
        return spaces[index];
    }

    @Override
    public String toString() {
        return "Activation Area";
    }
}
