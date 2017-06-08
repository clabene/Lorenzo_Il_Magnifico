package logic.board;

import logic.actionSpaces.ActionSpace;

/**
 * Created by Pinos on 26/05/2017.
 */
public interface Area {
    void show();
    ActionSpace getActionSpace(int index);
    String toString();
}
