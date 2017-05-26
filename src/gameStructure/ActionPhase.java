package gameStructure;

import actionSpaces.ActionSpace;
import areas.Area;
import areas.Board;
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


    public void putFamilyMemberOnActionSpace(){

        FamilyMember familyMember = player.selectFamilyMember();
        Area area = player.selectArea(board);
        ActionSpace actionSpace = player.selectActionSpace(area);
        incrementFamilyMemberValueRequest(player);



        if(!familyMember.getInActionSpace() &&  !actionSpace.getCovered()  ){
            if(familyMember.getValue() < actionSpace.getMinValueToPlaceFamiliar() &&){
                familyMember.setInActionSpace(true);
                actionSpace.familiarAdded();
                actionSpace.action(player);
            }else {
                while
                incrementFamilyMemberValueRequest(player);

            }

        }





    }

    public void incrementFamilyMemberValueRequest(Player player){
        boolean b = true;
        while(b) {

            System.out.println("vuoi sacrificare qualche schiavo? Si---> digita quanti; No--->digita 0\n");
            Scanner input = new Scanner(System.in);

            if (player.lose(new SetOfResources( new Slave(input.nextInt())))){
                b = false;
            }else{
                System.out.println("Non hai abbastanza schiavi\n");
            }
        }
    }











    public void addFamiliar (ActionSpace actionSpace, FamilyMember familyMember){



    }



}
