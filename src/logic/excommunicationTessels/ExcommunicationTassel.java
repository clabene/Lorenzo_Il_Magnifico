package logic.excommunicationTessels;

import logic.gameStructure.PeriodNumber;
import logic.player.Player;

import java.io.Serializable;

/**
 * Created by IBM on 02/06/2017.
 */
public abstract class ExcommunicationTassel implements Serializable {

    private PeriodNumber periodNumber;
    private String id;

    public ExcommunicationTassel(PeriodNumber periodNumber, String id){
        this.periodNumber = periodNumber;
        this.id = id;
    }

    public abstract void activate(Player player);

    public String getId(){
        return id;
    }

}
