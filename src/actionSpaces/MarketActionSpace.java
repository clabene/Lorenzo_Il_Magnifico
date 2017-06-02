package actionSpaces;

import interfaces.Gainable;
import player.Player;
import resources.Money;

/**
 * Created by IBM on 19/05/2017.
 */
public class MarketActionSpace extends ActionSpace {

    public MarketActionSpace(Gainable ... gainables){
        super(1, ActionSpaceType.MARKET, 1, gainables);
    }

    public boolean action(Player player) {
        return true;
    }

    public static void main(String[] a){
        MarketActionSpace m = new MarketActionSpace(new Money(5));
        Player p = new Player();
        m.action(p);
        System.out.println(p.getPlank().getSetOfResources());

    }


}
