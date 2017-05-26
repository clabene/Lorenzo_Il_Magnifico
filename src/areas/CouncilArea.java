package areas;

import actionSpaces.CouncilActionSpace;

/**
 * Created by Pinos on 25/05/2017.
 */
public class CouncilArea implements Area {
    private CouncilActionSpace space;

    public CouncilActionSpace getCouncilSpace(){
        return this.space;
    }
}
