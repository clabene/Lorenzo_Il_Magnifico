package board;

import actionSpaces.ActionSpace;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ChurchArea implements Area {

    @Override
    public ActionSpace getActionSpace(int index) {
        return null;
    }
    @Override
    public void show() {
    }

    @Override
    public String toString() {
        return "Church Area";
    }
}
