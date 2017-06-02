package excommunicationTessels;


import actionSpaces.ActionSpaceType;
import bonuses.BonusOnFamilyMemberPlacement;
import cards.CardType;
import gameStructure.PeriodNumber;
import player.Player;

/**
 * Created by IBM on 02/06/2017.
 */
public class MalusOnFamilyMemberPlacementTessel extends ExcommunicationTassel{

    private PeriodNumber periodNumber;
    private ActionSpaceType actionSpaceType;
    private CardType cardType;
    private int malus;

    /*
    * if action space type is market and malus = Integer.MIN_VALUE the "no market for you" tassel is implemented too
    * */

    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber, CardType cardType, int malus){
        super(periodNumber);
        this.cardType = cardType;
        this.malus = malus;
    }
    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber, ActionSpaceType actionSpaceType, int malus){
        super(periodNumber);
        this.actionSpaceType = actionSpaceType;
        this.malus = malus;
    }
    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber){
        super(periodNumber);
    }


    @Override
    public void activate(Player player) {
        if(actionSpaceType == null && cardType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(-1));
        if(actionSpaceType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(cardType, -1*malus));
        if(cardType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(actionSpaceType, -1*malus));

    }

}
