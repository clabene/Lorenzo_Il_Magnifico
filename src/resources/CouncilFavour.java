package resources;

import interfaces.Gainable;
import player.Player;
import utility.StaticVariables;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IBM on 24/05/2017.
 */
public class CouncilFavour implements Gainable {

    private int numberOfFavours = 1;

    private Gainable[] favours = StaticVariables.COUNCIL_FAVOURS;

    public CouncilFavour(int numberOfFavours){
        if(numberOfFavours >= favours.length) numberOfFavours = favours.length;
        this.numberOfFavours = numberOfFavours;
    }

    @Override
    public void gainedByPlayer(Player player){

        Scanner input = new Scanner(System.in);

        while(numberOfFavours > 0){
            //favours are printed
            toString();

            //player selects favour
            int chosenIndex = input.nextInt()-1;

            //favour gained by player (calling gain??)
            player.gain(favours[chosenIndex]);

            //remove favour from ArrayList
            favours[chosenIndex] = null;

            numberOfFavours--;
        }

    }

    @Override
    public String toString(){
        System.out.println("wella");
        int i = 0;
        String toReturn = "";
        for(Gainable tmp : favours) {
            if(tmp != null) {
                i++;
                toReturn += i + " " + tmp.toString() + "\n";
            }
        }
        return toReturn;
    }


    public static void main(String[] args){
        CouncilFavour c = new CouncilFavour(2);
        System.out.println(c);

        Player p = new Player();
        c.gainedByPlayer(p);
        System.out.println("player: " + p.getPlank().getSetOfResources() +" "+ p.getFaithPoints() + " " + p.getMilitaryPoints());


    }





}
