package board;

import actionSpaces.*;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ActivationArea implements Area {

    private ActivationActionSpace firstProductionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION );
    private ActivationActionSpace firstHarvestSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);
    private ActivationActionSpace secondProductionSpace = new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION );
    private ActivationActionSpace secondHarvestSpace = new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST );


}
