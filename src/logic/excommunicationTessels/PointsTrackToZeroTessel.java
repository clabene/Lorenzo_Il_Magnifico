package logic.excommunicationTessels;

import logic.cards.Card;
import logic.cards.CardType;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.PeriodNumber;
import logic.player.Player;
import logic.player.VictoryPoint;

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
                tmp.setPermanentEffect(new ReceiveGainablesEffect(new VictoryPoint(0)));
    }
}
