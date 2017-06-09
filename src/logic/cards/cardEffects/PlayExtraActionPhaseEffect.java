package logic.cards.cardEffects;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.CouncilActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.cards.Card;
import logic.cards.CardType;
import logic.gameStructure.ActionPhase;
import logic.gameStructure.Game;
import logic.interfaces.Gainable;
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
        for(ActionSpace tmp : player.getBoard().getHashMap().values()){
            if((tmp instanceof TowerActionSpace)) continue;
            TowerActionSpace tmp1 = (TowerActionSpace) tmp;
            if(tmp1.getCard() != null && this.cardType == null) //cardType == null -> i take all tower action space
                this.actionSpaces.add(tmp1);
            else if(tmp1.getCard() != null && tmp1.getCard().getCardType() == this.cardType)
                this.actionSpaces.add(tmp1);
            else break;
        }
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
        if(cardType != null && actionSpaces.isEmpty()) addActionSpaces(player);

        ActionPhase actionPhase = new ActionPhase();

        while(true) {
            if(isPhasePlayable(player)) break;

            ActionSpace selectedActionSpace = new CouncilActionSpace();//player.selectActionSpace(actionSpaces.toArray(new ActionSpace[actionSpaces.size()]));
            //todo input from client is needed here. See if Game or GameRoom is needed instead of ActionPhase

            if(selectedActionSpace == null) continue;

            // slaves sacrifices
            actionPhase.incrementFamilyMemberValueRequest(player, familyMember);

            // bonuses activation
            actionPhase.activateBonuses(player ,selectedActionSpace);

            if(actionPhase.putFamilyMemberOnActionSpace(player,familyMember, selectedActionSpace)) break;

            Card card = ( (TowerActionSpace) selectedActionSpace).getCard();
            selectedActionSpace = new TowerActionSpace(selectedActionSpace.getMinValueToPlaceFamiliar(), selectedActionSpace.getBonus().toArray(new Gainable[selectedActionSpace.getBonus().size()]));
            ((TowerActionSpace)selectedActionSpace).setCard(card);
        }

        // family member gets removed from game
        this.familyMember = null;

    }

}
