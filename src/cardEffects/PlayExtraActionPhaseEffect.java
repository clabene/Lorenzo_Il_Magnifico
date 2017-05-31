package cardEffects;

import actionSpaces.ActionSpace;
import actionSpaces.ActivationActionSpace;
import actionSpaces.TowerActionSpace;
import board.Tower;
import cardEffects.bonuses.Bonus;
import cards.CardType;
import gameStructure.ActionPhase;
import player.FamilyMember;
import player.Player;
import resources.SetOfResources;
import resources.Slave;

import java.util.ArrayList;
import java.util.Scanner;

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
        this.cardType = cardType;
    }

    /*
    * Tower action spaces with a card of the given type are added to actionSpaces
    * */
    private void addActionSpaces(Player player){
        for(Tower tmp : player.getBoard().getTowerArea().getTowers())
            for(TowerActionSpace tmp1 : tmp.getSpaces()) //itero sugli spazi azione delle torri
                if(tmp1.getCard() != null && tmp1.getCard().getCardType() == this.cardType)
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

    private void incrementFamilyMemberValueRequest(Player player, FamilyMember familyMember) {
        boolean b = true;
        while (b) {
            System.out.println("Quanti schiavi vuoi sacrificare? (Nessuno = 0)\n");
            Scanner input = new Scanner(System.in);
            int quantity = input.nextInt();
            if (player.lose(new SetOfResources(new Slave(quantity)))) {
                familyMember.incrementFamilyMemberValue(quantity);
                b = false;
            } else
                System.out.println("Non hai abbastanza schiavi\n");
        }
    }
    private void activateBonuses(Player player, ActionSpace actionSpace){
        for(Bonus tmp : player.getBonuses())
            tmp.activateBonus(actionSpace);
    }


    @Override
    public void activate(Player player) {

        this.actionPhase = new ActionPhase(player, null);

        if(cardType != null) addActionSpaces(player);

        while(true) {
            if(isPhasePlayable(player)) break;

            //select action space
            ActionSpace selectedActionSpace = player.selectActionSpace(actionSpaces.toArray(new ActionSpace[actionSpaces.size()]));
            if(selectedActionSpace == null) continue;

            // slaves sacrifices
            actionPhase.incrementFamilyMemberValueRequest(player, familyMember);

            // bonuses activation
            actionPhase.activateBonuses(selectedActionSpace);

            if(actionPhase.putFamilyMemberOnActionSpace(familyMember, selectedActionSpace)) break;

            //todo restore board as if no bonus was activated (see comment in ActionPhase class for some idea)
        }

        // family member gets removed from game
        this.familyMember = null;

    }








}
