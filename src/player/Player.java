package player;

import pointsTrack.FaithPointsTrack;
import pointsTrack.LandCardsPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import pointsTrack.PersonCardsPointsTrack;
import cards.Card;
import interfaces.Expendable;
import interfaces.Gainable;
import resources.Resource;

/**
 * Created by IBM on 14/05/2017.
 */
public class Player {

    private int victoryPoints = 0;
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

    public void spend(Expendable ... e) {
        //todo
    }

    public void takeCard(Card card){
        plank.getCards().cardAdded(card);
    }

    public void addResourcesToPlank(Resource ... resources){
        plank.getSetOfResources().resourcesAdded(resources);
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
}
