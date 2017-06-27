package logic.cards.cardEffects;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.cards.CardType;
import logic.player.ExtraAction;
import logic.player.FamilyMember;
import logic.player.Player;

import java.util.ArrayList;


/**
 * Created by IBM on 30/05/2017.
 */
public class PlayExtraActionPhaseEffect implements CardEffect{

    private ArrayList<ActionSpace> actionSpaces = new ArrayList<>();
    private int valueOfFamilyMember;
    private CardType cardType;

    public PlayExtraActionPhaseEffect(int valueOfFamilyMember, ActivationActionSpace activationActionSpace){
        this.valueOfFamilyMember = valueOfFamilyMember;
        this.actionSpaces.add(activationActionSpace);
    }
    public PlayExtraActionPhaseEffect(int valueOfFamilyMember, CardType cardType){ //action spaces -> one of the towers
        this.valueOfFamilyMember = valueOfFamilyMember;
        this.cardType = cardType;
    }
    public PlayExtraActionPhaseEffect(int valueOfFamilyMember){ //action spaces -> all of the towers
        this.valueOfFamilyMember = valueOfFamilyMember;
    }

    /*
    * Tower action spaces with a card of the given type are added to actionSpaces
    * */
    private void addTowerActionSpaces(Player player){
        for(ActionSpace tmp : player.getBoard().getHashMap().values()){
            if(!(tmp instanceof TowerActionSpace)) continue;
            TowerActionSpace tmp1 = (TowerActionSpace) tmp;
            if(tmp1.getCard() != null && this.cardType == null) //cardType == null -> take all tower action spaces
                this.actionSpaces.add(tmp1);
            else if(tmp1.getCard() != null && tmp1.getCard().getCardType() == this.cardType)
                this.actionSpaces.add(tmp1);
        }
    }

    private boolean isPhasePlayable(Player player) {
        for(ActionSpace tmp : actionSpaces)
            if (player.getPlank().getSetOfResources().getQuantityOfSlaves() + valueOfFamilyMember >= tmp.getMinValueToPlaceFamiliar())
                return true;

        System.out.println("You cannot make your extra move");
        return false;
    }

    @Override
    public String toString() {
        return " extra action";
    }

    @Override
    public void activate(Player player) {
        if( actionSpaces.isEmpty()) addTowerActionSpaces(player);

        ExtraAction extraAction = new ExtraAction(actionSpaces, valueOfFamilyMember);

        player.setExtraAction(extraAction);

    }

}
