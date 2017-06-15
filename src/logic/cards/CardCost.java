package logic.cards;

import logic.exceptions.NegativePointsException;
import logic.exceptions.NegativeResourceQuantityException;
import logic.interfaces.Gainable;
import logic.interfaces.Losable;
import logic.player.Player;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.SetOfResources;
import logic.resources.Wood;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by IBM on 13/05/2017.
 */
public class CardCost implements Losable, Serializable {

    private SetOfResources resourcesCost;

    private Losable selectedCost;

    private MilitaryPointsTrack militaryPointsCost;
    private int necessaryQuantityOfMilitaryPoints;

    public CardCost(SetOfResources resourcesCost){
        this.resourcesCost = resourcesCost;
        this.selectedCost = resourcesCost;
    }

    public CardCost(MilitaryPointsTrack militaryPointsCost, int necessaryQuantityOfMilitaryPoints){
        this.militaryPointsCost = militaryPointsCost;
        this.necessaryQuantityOfMilitaryPoints = necessaryQuantityOfMilitaryPoints;
        this.selectedCost = militaryPointsCost;
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
        else if(resourcesCost == null) {
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
            if(i == 1) {
                selectedCost = resourcesCost;
                resourcesCost.lostByPlayer(player);
            } else {
                selectedCost = militaryPointsCost;
                payMilitaryPoints(player);
            }
        }
    }

/*
    public static void main(String[] a){
        Player player = new Player("", new Wood(10));
        player.gain(new MilitaryPointsTrack(10));

        CardCost cardCost = new CardCost(new SetOfResources(new Wood(7)), new MilitaryPointsTrack(3), 10);

        player.lose(cardCost);

        System.out.println(player.getMilitaryPoints());
        System.out.println(player.getPlank().getSetOfResources());

        player.gain( (Gainable) cardCost.getSelectedCost());

        System.out.println(player.getMilitaryPoints());
        System.out.println(player.getPlank().getSetOfResources());
    }
*/


    public Losable getSelectedCost() {
        return selectedCost;
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
