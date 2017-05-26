package cardEffects;

import cards.Card;
import cards.CardType;
import interfaces.Gainable;
import player.Player;

import java.util.ArrayList;

/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesForCardTypeEffect implements CardEffect {

    private ArrayList<Gainable> gainables = new ArrayList<>();
    private CardType cardType;

    public ReceiveGainablesForCardTypeEffect(CardType cardType, Gainable ... gainables){
        for(Gainable tmp : gainables)
            this.gainables.add(tmp);
        this.cardType = cardType;
    }

    private Card[] getCardsOfGivenType(Player player){
        if(cardType == CardType.LAND) return player.getPlank().getCards().getLandCards();
        if(cardType == CardType.BUILDING) return player.getPlank().getCards().getBuildingCards();
        if(cardType == CardType.PERSON) return player.getPlank().getCards().getPersonCards();
        if(cardType == CardType.VENTURE) return player.getPlank().getCards().getVentureCards();
        return new Card[0];
    }

    public void activate(Player player){
        for(Card tmp : getCardsOfGivenType(player))
            player.gain((Gainable[]) gainables.toArray());
    }


}
