package cards;

import exceptions.NegativePointsException;
import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import player.Player;
import pointsTracks.MilitaryPointsTrack;
import resources.SetOfResources;

import java.util.Scanner;

/**
 * Created by IBM on 13/05/2017.
 */
public class CardCost implements Losable {

    /*
    * Right now this class can be easily replaced by Expandable objects. It will be worth not deleting if will encapsulate more functionality
    *
    * For example, it might turn useful when checking if a player has enough resources for taking a card from the tower
    *
    * */

    private SetOfResources resourcesCost;

    private MilitaryPointsTrack militaryPointsCost;
    private int necessaryQuantityOfMilitaryPoints;

    public CardCost(SetOfResources resourcesCost){
        this.resourcesCost = resourcesCost;
    }
    public CardCost(MilitaryPointsTrack militaryPointsCost, int necessaryQuantityOfMilitaryPoints){
        this.militaryPointsCost = militaryPointsCost;
        this.necessaryQuantityOfMilitaryPoints = necessaryQuantityOfMilitaryPoints;
    }
    public CardCost(SetOfResources resourcesCost, MilitaryPointsTrack militaryPointsCost, int necessaryQuantityOfMilitaryPoints){
        this.resourcesCost = resourcesCost;
        this.militaryPointsCost = militaryPointsCost;
        this.necessaryQuantityOfMilitaryPoints = necessaryQuantityOfMilitaryPoints;
    }

    public SetOfResources getResourcesCost() {
        return resourcesCost;
    }

    public void setResourcesCost(SetOfResources resourcesCost) {
        this.resourcesCost = resourcesCost;
    }

    public MilitaryPointsTrack getMilitaryPointsCost() {
        return militaryPointsCost;
    }

    @Override
    public void lostByPlayer(Player player) throws NegativeResourceQuantityException, NegativePointsException {
        if(militaryPointsCost == null)
            resourcesCost.lostByPlayer(player);
        if(resourcesCost == null) {
            payMilitaryPoints(player);
        }
        else {
            printMenu();
            int i = getIntegerFromPlayer();
            while(i != 1 && i != 2) {
                System.out.println("Invalid input");
                printMenu();
                i = getIntegerFromPlayer();
            }
            if(i == 1) resourcesCost.lostByPlayer(player);
            else payMilitaryPoints(player);
        }

    }

    private void payMilitaryPoints(Player player) throws NegativeResourceQuantityException, NegativePointsException {
        if(player.getMilitaryPoints().getTrackPosition().getValue() < necessaryQuantityOfMilitaryPoints) {
            System.out.println("You don't have enough military points to pay the card");
            throw new  NegativePointsException(); //might wanna have a new exception for this
        }
        else militaryPointsCost.lostByPlayer(player);
    }

    private int getIntegerFromPlayer(){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private void printMenu(){
        System.out.println("How do you want to pay?");
        System.out.println("1 " + resourcesCost);
        System.out.println("2 " + militaryPointsCost);
    }


}
