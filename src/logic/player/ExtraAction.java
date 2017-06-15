package logic.player;

import logic.actionSpaces.ActionSpace;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IBM on 12/06/2017.
 */
public class ExtraAction implements Serializable{

    private ArrayList<ActionSpace> actionSpaces = new ArrayList<>();
    private int familyMemberValue;

    public ExtraAction(ArrayList<ActionSpace> actionSpaces, int familyMemberValue) {
        this.actionSpaces = actionSpaces;
        this.familyMemberValue = familyMemberValue;
    }

    public int getFamilyMemberValue() {
        return familyMemberValue;
    }

    public ArrayList<ActionSpace> getActionSpaces() {
        return actionSpaces;
    }
}
