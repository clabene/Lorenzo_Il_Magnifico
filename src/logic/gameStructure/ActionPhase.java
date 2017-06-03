package logic.gameStructure;

import logic.actionSpaces.ActionSpace;

import logic.actionSpaces.ActionSpaceType;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Area;
import logic.board.Board;
import logic.bonuses.Bonus;
import logic.bonuses.BonusOnFamilyMemberPlacement;
import logic.cards.Card;
import logic.interfaces.Gainable;
import logic.interfaces.Losable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.SetOfResources;
import logic.resources.Slave;

import java.util.Scanner;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ActionPhase {

    private Player player;
    private Board board;

    public ActionPhase(Player player, Board board){
        this.player = player;
        this.board = board;
    }


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
            */
            Card card = ( (TowerActionSpace) actionSpace).getCard();
            actionSpace = new TowerActionSpace(actionSpace.getMinValueToPlaceFamiliar(), actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
            ((TowerActionSpace)actionSpace).setCard(card);

        }
    }

    public static void main(String[] ar){
        Player p = new Player(new Slave(2));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.MARKET, 1));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.TOWER, 6));
        p.getBonuses().add(new BonusOnFamilyMemberPlacement(ActionSpaceType.MARKET, -1));
        ActionPhase a = new ActionPhase(p, new Board());
        a.playActionPhase();
        System.out.println(p.getPlank().getSetOfResources());
    }

    public boolean putFamilyMemberOnActionSpace(FamilyMember familyMember, ActionSpace actionSpace) {
        if (familyMember.getValue() >= actionSpace.getMinValueToPlaceFamiliar() ) {
            /*
            familyMember.setInActionSpace(true); // Should not this go inside the if statement?
                                                 // Indeed, if a tower card is too expensive for the player, they will
                                                 // take their familiar back!
            if(actionSpace.action(player))
                player.gain(actionSpace.familiarAdded());
            */

            actionSpace.familyMemberAdded(familyMember);
            familyMember.setInActionSpace(true);
            player.gain(actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
            if(!actionSpace.action(player)) {
                player.lose(actionSpace.getBonus().toArray(new Losable[actionSpace.getBonus().size()])); //todo check if this works
                actionSpace.familyMemberRemoved(familyMember);
                familyMember.setInActionSpace(false);
                return false;
            }
            return true;
        }else
            System.out.println("Your family member is not valuable enough for this action space\n");

        return false;
    }

    private boolean checkPhasePlayable() {
        if (player.getFamilyMembersAvailable().size() == 1 &&
                player.getFamilyMembersAvailable().contains(new FamilyMember(null, 0)) &&
                player.getPlank().getSetOfResources().getQuantityOfSlaves() == 0) {

            System.out.println("Sei spacciato");
            return false;
        }
        return true;

    }


    public void incrementFamilyMemberValueRequest(Player player, FamilyMember familyMember) {
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


    private FamilyMember selectionFamilyMemberPhase(){
        FamilyMember familyMember = player.selectFamilyMember();
        if (familyMember.getInActionSpace()) {
            System.out.println("Il familiare è occupato");
            return null;
        }
        return familyMember;


    }

    private ActionSpace selectionActionSpacePhase(){
        Area area = player.selectArea(board);
        ActionSpace actionSpace = player.selectActionSpace(area);
        if (actionSpace.getCovered()) {
            System.out.println("Spazio azione occupato");
            return null;
        }
        return actionSpace;
    }

    public void activateBonuses(ActionSpace actionSpace){
        for(Bonus tmp : player.getBonuses())
            tmp.activateBonus(actionSpace);
    }

}


