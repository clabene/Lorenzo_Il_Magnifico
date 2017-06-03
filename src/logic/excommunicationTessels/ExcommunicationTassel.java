package logic.excommunicationTessels;

import logic.gameStructure.PeriodNumber;
import logic.player.Player;

/**
 * Created by IBM on 02/06/2017.
 */
public abstract class ExcommunicationTassel {

    private PeriodNumber periodNumber;

    public ExcommunicationTassel(PeriodNumber periodNumber){
        this.periodNumber = periodNumber;
    }

    public abstract void activate(Player player);

}
