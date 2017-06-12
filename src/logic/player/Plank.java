package logic.player;

import logic.cards.SetOfCards;
import logic.resources.CouncilFavour;
import logic.resources.Resource;
import logic.resources.SetOfResources;

import java.util.ArrayList;

/**
 * Created by IBM on 15/05/2017.
 */
public class Plank {
    private SetOfCards cards;

    private SetOfResources resources;
    private SetOfResources separateResources;
    private boolean toUseSeparateResources = false;

    private ArrayList<CouncilFavour> councilFavours = new ArrayList<>();



    public Plank(Resource ... resources){
        this.cards = new SetOfCards();
        this.resources = new SetOfResources(resources);
        this.separateResources = new SetOfResources();
    }

    public SetOfCards getCards() {
        return cards;
    }

    public ArrayList<CouncilFavour> getCouncilFavours() {
        return councilFavours;
    }

    public void setToUseSeparateResources(boolean toUseSeparateResources) {
        this.toUseSeparateResources = toUseSeparateResources;
    }

    public SetOfResources getSetOfResources() {
        if(toUseSeparateResources)
            return separateResources;
        return resources;
    }

    public void dump(){
        resources.resourcesAdded(separateResources.getResources());
        separateResources = new SetOfResources();
    }


}
