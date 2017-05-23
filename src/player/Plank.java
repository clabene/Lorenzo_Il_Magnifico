package player;

import cards.SetOfCards;
import resources.Resource;
import resources.SetOfResources;

/**
 * Created by IBM on 15/05/2017.
 */
public class Plank {

    private SetOfCards cards;
    private SetOfResources resources;

    public Plank(Resource ... resources){
        this.cards = new SetOfCards();
        this.resources = new SetOfResources(resources);
    }

    public SetOfCards getCards() {
        return cards;
    }

    public SetOfResources getSetOfResources() {
        return resources;
    }


}
