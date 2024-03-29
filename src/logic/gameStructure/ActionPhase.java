package logic.gameStructure;

import logic.actionSpaces.ActionSpace;

import logic.actionSpaces.ActionSpaceType;
import logic.bonuses.Bonus;
import logic.bonuses.BonusOnFamilyMemberPlacement;
import logic.interfaces.Gainable;
import logic.interfaces.Losable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.SetOfResources;
import logic.resources.Slave;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ActionPhase implements Serializable{

    //private Player player;
    //private Board board;
/*
    public ActionPhase(Player player, Board board){
        this.player = Player;
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.player = Player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
*/
    /*
    public void playActionPhase(){

        while(true){

            if(!checkPhasePlayable()) return; //todo
                                              // what about if player has bonuses?!
                                              // We could make a copy of the board, apply the bonuses to all of its
                                              // available action spaces and then make a control on checkPhasePlayable
                                              // using that copy of the board (i.e. if i have 0 slaves and only a 0-value
                                              // family member but there is an available action space with minValueToPlaceFamilyMember < 0,
                                              // i still can (/must) make an action)
            FamilyMember familyMember = selectionFamilyMemberPhase();
            if(familyMember == null) continue;

            ActionSpace actionSpace = selectionActionSpacePhase();
            if(actionSpace == null) continue;

            activateBonuses(actionSpace); //bonuses of player are activated

            incrementFamilyMemberValueRequest(player, familyMember);

            if(putFamilyMemberOnActionSpace(familyMember, actionSpace)) return;
            /*todo
            here board is to be restored as if no bonus was activated (if a card cost was decreased but the card was not taken,
            the cost has to go back to its previous value)

            Observation: the only actionSpaces that can cause the problem described up-here are the tower action spaces, therefore
            something like this could be a solution:

            Card card = ( (TowerActionSpace) actionSpace).getCard();
            actionSpace = new TowerActionSpace(actionSpace.getMinValueToPlaceFamiliar(), actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
            ((TowerActionSpace)actionSpace).setCard(card);

        }
    }
    */
/*
    public static void main(String[] ar){
        Player p = new Player(null, new Slave(2));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.MARKET, 1));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.TOWER, 6));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.MARKET, -1));
        //ActionPhase a = new ActionPhase(p, new Board());
        //a.playActionPhase();
        System.out.println(p.getPlank().getSetOfResources());
    }
*/
    public boolean putFamilyMemberOnActionSpace(Player player, FamilyMember familyMember, ActionSpace actionSpace) {

        actionSpace.familyMemberAdded(familyMember);
        familyMember.setInActionSpace(true);
        activateBonuses(player, actionSpace);

        if (familyMember.getValue() >= actionSpace.getMinValueToPlaceFamiliar() ) {
            if(actionSpace.getBonus() != null) player.gain(actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
            if(!actionSpace.action(player)) {
                //restore previous situation
                player.lose(actionSpace.getBonus().toArray(new Losable[actionSpace.getBonus().size()]));
                actionSpace.familyMemberRemoved(familyMember);
                familyMember.setInActionSpace(false);
                return false;
            }
            return true;
        }else {
            //restore previous situation
            System.out.println("Your family member is not valuable enough for this action space\n");
            actionSpace.familyMemberRemoved(familyMember);
            familyMember.setInActionSpace(false);
        }
        return false;
    }

    public boolean checkPhasePlayable(Player player) {
        if (player.getFamilyMembersAvailable().size() == 1 &&
                player.getFamilyMembersAvailable().contains(new FamilyMember(null, 0, player.getId())) &&
                player.getPlank().getSetOfResources().getQuantityOfSlaves() == 0
                ||
                player.getFamilyMembersAvailable().size() == 0) {

            System.out.println("Sei spacciato");
            return false;
        }
        return true;

    }

    public boolean incrementFamilyMemberValueRequest(Player player, FamilyMember familyMember, int quantity) {
        if(player.lose(new Slave(quantity))) {

            if(familyMember.getColor() == null ){
                for(FamilyMember tmp: player.getFamilyMembers()){
                    if(tmp.getColor()== null){
                        tmp.incrementFamilyMemberValue(quantity);
                        return true;
                    }
                }

            }
            for(FamilyMember tmp: player.getFamilyMembers()){
                if(tmp.getColor()!= null && tmp.getColor().equals(familyMember.getColor())){
                    tmp.incrementFamilyMemberValue(quantity);
                    return true;
                }
            }
            //familyMember.incrementFamilyMemberValue(quantity);

            return true;
        }
        return false;
    }

    public void activateBonuses(Player player, ActionSpace actionSpace){
        for(Bonus tmp : player.getBonuses()){
            System.out.println(tmp);
            tmp.activateBonus(actionSpace);
        }
    }

}


