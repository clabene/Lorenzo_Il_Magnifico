package player;

import actionSpaces.ActionSpace;
import exceptions.NegativePointsException;
import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import pointsTrack.FaithPointsTrack;
import pointsTrack.LandCardsPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import pointsTrack.PersonCardsPointsTrack;
import cards.Card;
import interfaces.Gainable;
import resources.Resource;
import areas.Area;

/**
 * Created by IBM on 14/05/2017.
 */
public class Player {

    private int victoryPoints = 0; //todo
    private MilitaryPointsTrack militaryPoints = new MilitaryPointsTrack();
    private FaithPointsTrack faithPoints = new FaithPointsTrack();
    private PersonCardsPointsTrack personCardsPoints = new PersonCardsPointsTrack();
    private LandCardsPointsTrack landCardsPoints = new LandCardsPointsTrack();

    private Plank plank;

    public Player(Resource ... resources){
        this.plank = new Plank(resources);
    }


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
    * */
    public void lose(Losable... losables) {
        for (Losable tmp : losables)
            try {
                tmp.lostByPlayer(this);
            } catch (NegativeResourceQuantityException | NegativePointsException e) {
                e.printStackTrace();
            }
    }




    public Area selectArea(Area area){
        System.out.println("Quale area vuoi selezionare?");

        return area;

    }

    public ActionSpace selectActionSpace (ActionSpace actionSpace){
        System.out.println("Quale spazio azione vuoi selezionare?");
        return actionSpace;
    }

    public FamilyMember selectFamilyMember (FamilyMember familyMember) {
        System.out.println("Quale familiare vuoi selezionare?");
        return familyMember;
    }





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

    public void loseVictoryPoints(int points) throws NegativePointsException {
        if(this.victoryPoints < points) throw new NegativePointsException();
        this.victoryPoints -= points;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}
