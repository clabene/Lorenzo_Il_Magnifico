package logic.cards.cardEffects;

import logic.cards.Card;
import logic.cards.CardType;
import logic.interfaces.Gainable;
import logic.player.Player;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.Wood;


/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesForCardTypeEffect extends ReceiveGainablesEffect {

    private CardType cardType;

    public ReceiveGainablesForCardTypeEffect(CardType cardType, Gainable ... gainables){
        super(gainables);
        this.cardType = cardType;
    }


    private Card[] getCardsOfGivenType(Player player){
        if(cardType == CardType.LAND) return player.getPlank().getCards().getLandCards();
        else if(cardType == CardType.BUILDING) return player.getPlank().getCards().getBuildingCards();
        else if(cardType == CardType.PERSON) return player.getPlank().getCards().getPersonCards();
        else if(cardType == CardType.VENTURE) return player.getPlank().getCards().getVentureCards();
        return new Card[0];
    }

    @Override
    public void activate(Player player){
        for(Card tmp : getCardsOfGivenType(player))
            if(tmp != null)
                super.activate(player);
    }

    public static void main(String[] a){
        Player p = new Player();
        //p.getPlank().getCards().cardAdded(new LandCard("po", PeriodNumber.SECOND, 0, null, null));
        //p.getPlank().getCards().cardAdded(new LandCard("po", PeriodNumber.SECOND, 0, null, null));
        //p.getPlank().getCards().cardAdded(new LandCard("po", PeriodNumber.SECOND, 0, null, null));
        ReceiveGainablesForCardTypeEffect r = new ReceiveGainablesForCardTypeEffect(CardType.LAND, new Wood(2), new MilitaryPointsTrack(1));
        r.activate(p);
        System.out.println(p.getPlank().getSetOfResources());
        System.out.println(p.getMilitaryPoints());
    }



}
