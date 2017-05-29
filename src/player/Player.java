package player;

import actionSpaces.ActionSpace;
import board.Board;
import board.Color;
import exceptions.NegativePointsException;
import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import pointsTrack.FaithPointsTrack;
import pointsTrack.LandCardsPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import pointsTrack.PersonCardsPointsTrack;
import cards.Card;
import interfaces.Gainable;
import resources.*;
import board.Area;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IBM on 14/05/2017.
 */
public class Player {

    private String id;
    private int victoryPoints = 0; //todo
    private MilitaryPointsTrack militaryPoints = new MilitaryPointsTrack();
    private FaithPointsTrack faithPoints = new FaithPointsTrack();
    private PersonCardsPointsTrack personCardsPoints = new PersonCardsPointsTrack();
    private LandCardsPointsTrack landCardsPoints = new LandCardsPointsTrack();


    private Plank plank;

    public Player(Resource ... resources){
        this.plank = new Plank(resources);
    }

    private final int BLACK_FAMILY_MEMBER_INDEX = 0;
    private final int RED_FAMILY_MEMBER_INDEX = 1;
    private final int WHITE_FAMILY_MEMBER_INDEX = 2;
    private final int NEUTRAL_FAMILY_MEMBER_INDEX = 3;

    FamilyMember [] familyMembers = {new FamilyMember(Color.BLACK, 0), new FamilyMember(Color.RED, 0),
            new FamilyMember(Color.WHITE, 0), new FamilyMember(null, 0)};



    /*
    * Players gains all the points and all the resources given as parameters
    * */
    public void gain(Gainable ... gainables) {
        for (Gainable tmp : gainables)
            tmp.gainedByPlayer(this);
    }

    /*
    * Players loses all the points and all the resources given as parameter. Further information follow:
    *
    * Player loses all the losable objects that can lose without an exception to be thrown. The catching of an Exception will not
    * cause the method to stop. Also, player will not lose a portion of a SetOfResources, but either all of it or none of it.
    *
    * If player is asked to loose a certain quantity of some resource but has less of it, they won't lose any of that resource
    * and false will be returned. When it comes to points, they will always return true and lose all possible points.
    *
    * true: all was lost
    *
    * */
    public boolean lose(Losable... losables) {
        boolean toReturn = true;
        for (Losable tmp : losables)
            try {
                tmp.lostByPlayer(this);
            } catch (NegativeResourceQuantityException | NegativePointsException e) {
                toReturn = false;
            }
        return toReturn;
    }

    public static void main(String[] a){
        Player player = new Player(new Slave(21));
        player.gain(new FaithPointsTrack(10));
        boolean b = player.lose(new FaithPointsTrack(15), new Slave(20));
        System.out.println("fede "+ player.getFaithPoints().getTrackPosition().getValue());
        System.out.println(player.getPlank().getSetOfResources().toString());
        System.out.println(b);

    }


    public Area selectArea(Board board){
        System.out.println("Quale area vuoi selezionare?");
        board.show();
        Scanner input = new Scanner(System.in);
        int index = input.nextInt() -1 ;
        return board.getArea(index);


    }

    public ActionSpace selectActionSpace (Area area){

        System.out.println("Quale spazio azione vuoi selezionare?");
        area.show();
        Scanner input = new Scanner(System.in);
        int index = input.nextInt() -1 ;
        return area.getActionSpace(index);


    }

    public FamilyMember selectFamilyMember() {
        System.out.println("Quale familiare vuoi selezionare?");
        this.showFamilyMembers();
        Scanner input = new Scanner(System.in);
        int index = input.nextInt() -1;


        return familyMembers[index];
    }


    public void showFamilyMembers(){
        System.out.println("Scegli un familiare \n");
        int i = 0;
        for(FamilyMember tmp : familyMembers){
            i++;
            System.out.println(i+ " " + tmp.toString()+ " del giocatore " + this.id +"\n");
        }


    }



    public String getId(){return this.id;};

    public void takeCard(Card card){
        plank.getCards().cardAdded(card);
    }

    public void addResourcesToPlank(Resource ... resources){
        plank.getSetOfResources().resourcesAdded(resources);
    }

    public void removeResourcesFromPlank(Resource ... resources) throws NegativeResourceQuantityException {
        plank.getSetOfResources().resourcesSpent(resources);
    }

    public Plank getPlank() {
        return plank;
    }

    public MilitaryPointsTrack getMilitaryPoints() {
        return militaryPoints;
    }

    public FaithPointsTrack getFaithPoints() {
        return faithPoints;
    }

    public PersonCardsPointsTrack getPersonCardsPoints() {
        return personCardsPoints;
    }

    public LandCardsPointsTrack getLandCardsPoints() {
        return landCardsPoints;
    }

    public void addVictoryPoints(int points) {
        this.victoryPoints += points;
    }

    public void loseVictoryPoints(int points) {
        if(this.victoryPoints < points) this.victoryPoints = 0;
        this.victoryPoints -= points;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
/*
    public int getNumberOfFamilyMembersAvailable(){
        int i = 0;
        for(FamilyMember tmp: familyMembers){
            if(!tmp.getInActionSpace()){
                i++;
            }
        }
        return i;
    }
*/
    public ArrayList<FamilyMember> getFamilyMembersAvailable(){
        ArrayList<FamilyMember> available = new ArrayList<>();
        for(FamilyMember tmp: familyMembers){
            if(!tmp.getInActionSpace()){
                available.add(tmp);

            }
        }
        return available;
    }
}
