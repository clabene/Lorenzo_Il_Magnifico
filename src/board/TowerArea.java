package board;

import actionSpaces.ActionSpace;
import actionSpaces.TowerActionSpace;
import utility.StaticVariables;

import java.util.Scanner;


/**
 * Created by Pinos on 26/05/2017.
 */
public class TowerArea implements Area {
    //private TowerActionSpace[] spaces = StaticVariables.TOWER_ACTION_SPACES;
    private Tower[] towers = StaticVariables.TOWERS;

    public Tower[] getTowers() {
        return towers;
    }

    @Override
    public void show() {
        int i = 0;
        int j = 0;

        for(Tower tmp: towers ){
            i++;
            System.out.println(i+" Tower :"+ tmp.toString());
            for(TowerActionSpace tmp1: tmp.getSpaces()){
                j++;
                System.out.println(i+"."+j + "TowerActionSpace"+ tmp1.toString()+"\n");
            }

        }

    }



    @Override
    public ActionSpace getActionSpace(int index) {
        int index2;
        System.out.println("Inserisci lo spazio azione corrispondente");
        Scanner in = new Scanner(System.in);
        index2 = in.nextInt();
        return towers[index].getSpaces()[index2];
    }

    @Override
    public String toString(){
        return "Tower Area";
    }

    public Tower[] getTowers(){
        return this.towers;
    }
}
