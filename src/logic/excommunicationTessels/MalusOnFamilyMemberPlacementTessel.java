package logic.excommunicationTessels;


import logic.actionSpaces.ActivationActionSpaceType;
import logic.bonuses.BonusOnFamilyMemberPlacement;
import logic.cards.CardType;
import logic.gameStructure.PeriodNumber;
import logic.player.Player;

/**
 * Created by IBM on 02/06/2017.
 */
public class MalusOnFamilyMemberPlacementTessel extends ExcommunicationTassel{
    /*
    private PeriodNumber periodNumber;
    private ActionSpaceType actionSpaceType;
    private ActivationActionSpaceType activationActionSpaceType;
    private CardType cardType;
    private int malus;
    */
    private BonusOnFamilyMemberPlacement malusToGiveToPlayer;

    /*
    * Observation: if action space type is market and malus = Integer.MIN_VALUE, the "no market for you" tassel is implemented too
    * */

    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber, CardType cardType, int malus){
        super(periodNumber);
        //this.cardType = cardType;
        // this.malus = malus;
        this.malusToGiveToPlayer = new BonusOnFamilyMemberPlacement(cardType, -1*malus);

    }
    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber, ActivationActionSpaceType activationActionSpaceType /*ActionSpaceType actionSpaceType*/, int malus){
        super(periodNumber);
        //this.activationActionSpaceType = activationActionSpaceType;
        //this.actionSpaceType = ActionSpaceType.ACTIVATION;
        //this.actionSpaceType = actionSpaceType;
        //this.malus = malus;
        this.malusToGiveToPlayer = new BonusOnFamilyMemberPlacement(activationActionSpaceType, -1*malus);

    }
    public MalusOnFamilyMemberPlacementTessel(PeriodNumber periodNumber){
        super(periodNumber);
        this.malusToGiveToPlayer = new BonusOnFamilyMemberPlacement(-1);
    }


    @Override
    public void activate(Player player) {
        /*
        if(actionSpaceType == null && cardType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(-1));

        if(actionSpaceType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(cardType, -1*malus));


        if(cardType == null) player.getBonuses().add(new BonusOnFamilyMemberPlacement(activationActionSpaceType, -1*malus));
        */
        player.getBonuses().add(malusToGiveToPlayer);

    }

}
