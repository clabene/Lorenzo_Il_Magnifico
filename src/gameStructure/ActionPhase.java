package gameStructure;

import actionSpaces.ActionSpace;

import board.Area;
import board.Board;
import cardEffects.CardEffect;
import cards.Card;
import player.FamilyMember;
import player.Player;
import resources.SetOfResources;
import resources.Slave;

import java.util.Scanner;

/**
 * Created by Pinos on 25/05/2017.
 */
public class ActionPhase {

    private Player player;
    private Board board;


    public void playActionPhase(){


        while(true){

            if(!checkPhasePlayable()){return;}
            FamilyMember familyMember = selectionFamilyMemberPhase();
            if(familyMember == null){ continue; }

            ActionSpace actionSpace = selectionActionSpacePhase();
            if(actionSpace == null){continue;}

            incrementFamilyMemberValueRequest(player);

            if(putFamilyMemberOnActionSpace(familyMember, actionSpace)){ return;}


        }

    }

    public boolean putFamilyMemberOnActionSpace(FamilyMember familyMember, ActionSpace actionSpace) {
        if (familyMember.getValue() < actionSpace.getMinValueToPlaceFamiliar() ) {
            familyMember.setInActionSpace(true);
            actionSpace.familiarAdded();
            actionSpace.action(player);
            return true;
        }else
            return false;
    }

    public boolean checkPhasePlayable() {
        if (player.getFamilyMembersAvailable().size() == 1 &&
                player.getFamilyMembersAvailable().contains(new FamilyMember(null, 0)) &&
                player.getPlank().getSetOfResources().getQuantityOfSlaves() == 0) {


            System.out.println("Sei spacciato");
            return false;
        } else
            return true;

    }


    public void incrementFamilyMemberValueRequest(Player player) {
        boolean b = true;
        while (b) {

            System.out.println("Quanti schiavi vuoi sacrificare? (Nessuno = 0)\n");
            Scanner input = new Scanner(System.in);

            if (player.lose(new SetOfResources(new Slave(input.nextInt())))) {
                b = false;
            } else {
                System.out.println("Non hai abbastanza schiavi\n");
            }
        }
    }


    public void addFamiliar(ActionSpace actionSpace, FamilyMember familyMember) {


    }

    public FamilyMember selectionFamilyMemberPhase(){
        FamilyMember familyMember = player.selectFamilyMember();
        if (familyMember.getInActionSpace()) {
            System.out.println("Il familiare è occupato");
            return null;
        }else
            return familyMember;

    }

    public ActionSpace selectionActionSpacePhase(){
        Area area = player.selectArea(board);
        ActionSpace actionSpace = player.selectActionSpace(area);
        if (actionSpace.getCovered()) {
            System.out.println("Spazio azione occupato");
            return null;
        }
        else
            return actionSpace;

    }

}


