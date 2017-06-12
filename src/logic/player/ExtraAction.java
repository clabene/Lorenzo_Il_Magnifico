package logic.player;

import logic.actionSpaces.ActionSpace;
import logic.cards.CardType;

import java.util.ArrayList;

/**
 * Created by IBM on 12/06/2017.
 */
public class ExtraAction {

    private ArrayList<ActionSpace> actionSpaces = new ArrayList<>();
    private int familyMemberValue;
    private CardType cardType;

    public int getFamilyMemberValue() {
        return familyMemberValue;
    }

    public ArrayList<ActionSpace> getActionSpaces() {
        return actionSpaces;
    }
}
