package areas;

import actionSpaces.TowerActionSpace;
import pointsTrack.MilitaryPointsTrack;
import resources.*;


/**
 * Created by Pinos on 26/05/2017.
 */
public class TowerArea implements Area {
    TowerActionSpace [][] spaces = {new TowerActionSpace (new Wood(2)), new TowerActionSpace(new Stone(2)), new TowerActionSpace(new MilitaryPointsTrack(2)), new TowerActionSpace(new Money(2)),
    new TowerActionSpace(new Wood(1)),  }
}
