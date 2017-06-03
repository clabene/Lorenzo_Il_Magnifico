package logic.board;

import logic.actionSpaces.ActionSpace;

/**
 * Created by Pinos on 26/05/2017.
 */
public interface Area {
    public void show();
    public ActionSpace getActionSpace(int index);
    public String toString();
}
