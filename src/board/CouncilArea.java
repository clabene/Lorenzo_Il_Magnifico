package board;

import actionSpaces.ActionSpace;
import actionSpaces.CouncilActionSpace;

import java.util.ArrayList;

/**
 * Created by Pinos on 25/05/2017.
 */
public class CouncilArea implements Area {
    private CouncilActionSpace space = new CouncilActionSpace();

    public CouncilActionSpace getCouncilSpace(){
        return this.space;
    }

    @Override
    public void show() {
        System.out.println("CouncilActionSpace");
    }

    @Override
    public ActionSpace getActionSpace(int index) {
        return space;
    }

    public ArrayList<String> getTurnOrder(){
        return this.space.getFamilyMemberArrayList();
    }
}
