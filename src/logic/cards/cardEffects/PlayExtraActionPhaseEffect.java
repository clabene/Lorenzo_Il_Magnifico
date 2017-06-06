package logic.cards.cardEffects;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Tower;
import logic.cards.CardType;
import logic.gameStructure.ActionPhase;
import logic.player.FamilyMember;
import logic.player.Player;

import java.util.ArrayList;


/**
 * Created by IBM on 30/05/2017.
 */
public class PlayExtraActionPhaseEffect implements CardEffect{

    //this class is not very well implemented

    private ArrayList<ActionSpace> actionSpaces = new ArrayList<>();
    private FamilyMember familyMember;
    private CardType cardType;
    private ActionPhase actionPhase;

    public PlayExtraActionPhaseEffect(int valueOfFamilyMember, ActivationActionSpace activationActionSpace){
        this.familyMember = new FamilyMember(null, valueOfFamilyMember);
        this.actionSpaces.add(activationActionSpace);
    }
    public PlayExtraActionPhaseEffect(int valueOfFamilyMember, CardType cardType){
        this.familyMember = new FamilyMember(null, valueOfFamilyMember);
        this.cardType = cardType; //null -> all of them (towers)
    }
    public PlayExtraActionPhaseEffect(int valueOfFamilyMember){ //action spaces -> all of tower area
        this.familyMember = new FamilyMember(null, valueOfFamilyMember);
    }

    /*
    * Tower action spaces with a card of the given type are added to actionSpaces
    * */
    private void addActionSpaces(Player player){
        for(Tower tmp : player.getBoard().getTowerArea().getTowers())
            for(TowerActionSpace tmp1 : tmp.getSpaces()) //itero sugli spazi azione delle torri
                if(tmp1.getCard() != null && this.cardType == null) //cardType == null -> i take all tower action space
                    this.actionSpaces.add(tmp1);
                else if(tmp1.getCard() != null && tmp1.getCard().getCardType() == this.cardType)
                    this.actionSpaces.add(tmp1);
                else break;
    }

    //todo add check considering bonus
    private boolean isPhasePlayable(Player player) {
        for(ActionSpace tmp : actionSpaces)
            if (player.getPlank().getSetOfResources().getQuantityOfSlaves() + familyMember.getValue() >= tmp.getMinValueToPlaceFamiliar())
                return true;

        System.out.println("You cannot make your extra move");
        return false;
    }

    @Override
    public void activate(Player player) {

        this.actionPhase = new ActionPhase();

        if(cardType != null && actionSpaces.isEmpty()) addActionSpaces(player);

        while(true) {
            if(isPhasePlayable(player)) break;

            // select action space
            ActionSpace selectedActionSpace = player.selectActionSpace(actionSpaces.toArray(new ActionSpace[actionSpaces.size()]));
            if(selectedActionSpace == null) continue;

            // slaves sacrifices
            actionPhase.incrementFamilyMemberValueRequest(player, familyMember);

            // bonuses activation
            actionPhase.activateBonuses(player ,selectedActionSpace);

            if(actionPhase.putFamilyMemberOnActionSpace(player,familyMember, selectedActionSpace)) break;

            //todo restore board as if no bonus was activated (see comment in ActionPhase class for some idea)
        }

        // family member gets removed from game
        this.familyMember = null;

    }

}
