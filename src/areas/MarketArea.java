package areas;

import java.util.ArrayList;


import interfaces.Gainable;
import pointsTrack.MilitaryPointsTrack;
import resources.*;
import actionSpaces.MarketActionSpace;




/**
 * Created by Pinos on 25/05/2017.
 */
public class MarketArea {
    MarketActionSpace[] spaces = {new MarketActionSpace(new Money(5)), new  MarketActionSpace(new Slave(5)),
            new MarketActionSpace(new Money(2), new MilitaryPointsTrack(3)), new MarketActionSpace(new CouncilFavour(2)) };





}