package excommunicationTessels;

import cards.Card;
import cards.CardType;
import gameStructure.PeriodNumber;
import player.Player;

/**
 * Created by IBM on 02/06/2017.
 */
public class PointsTrackToZeroTessel extends ExcommunicationTassel{

    private CardType cardType;

    public PointsTrackToZeroTessel(CardType cardType){
        super(PeriodNumber.THIRD);
        this.cardType = cardType;
    }

    @Override
    public void activate(Player player) {
        if(cardType == CardType.LAND)
            player.getLandCardsPoints().setTrackPosition(0);
        else if(cardType == CardType.PERSON)
            player.getPersonCardsPoints().setTrackPosition(0);
        else if(cardType == CardType.VENTURE)
            for(Card tmp : player.getPlank().getCards().getVentureCards())
                tmp.setPermanentEffect(null);
    }
}
