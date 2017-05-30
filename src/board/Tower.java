package board;

import actionSpaces.ActionSpace;
import actionSpaces.TowerActionSpace;
import com.sun.corba.se.impl.oa.toa.TOA;
import interfaces.Gainable;
import resources.Resource;

/**
 * Created by Pinos on 30/05/2017.
 */
public class Tower {

    TowerActionSpace[] spaces;

    public Tower (TowerActionSpace[] spaces){
        this.spaces = spaces;
    }

    public TowerActionSpace[] getSpaces(){
        return this.spaces;
    }




}
