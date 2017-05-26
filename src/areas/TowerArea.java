package areas;

import actionSpaces.TowerActionSpace;
import pointsTrack.MilitaryPointsTrack;
import resources.*;


/**
 * Created by Pinos on 26/05/2017.
 */
public class TowerArea implements Area {
    private TowerActionSpace [][] spaces = {{new TowerActionSpace (7, new Wood(2)),
                                            new TowerActionSpace(7, new Stone(2)),
                                            new TowerActionSpace(7, new MilitaryPointsTrack(2)),
                                            new TowerActionSpace(7, new Money(2))},
                                            {new TowerActionSpace (5, new Wood(1)),
                                            new TowerActionSpace(5, new Stone(1)),
                                            new TowerActionSpace(5, new MilitaryPointsTrack(1)),
                                            new TowerActionSpace(5, new Money(1))},
                                            {new TowerActionSpace (3),
                                            new TowerActionSpace(3),
                                            new TowerActionSpace(3),
                                            new TowerActionSpace(3)},
                                            {new TowerActionSpace (1),
                                            new TowerActionSpace(1),
                                            new TowerActionSpace(1),
                                            new TowerActionSpace(1)}};

    public TowerActionSpace[][] getTowerActionSpaces(){
        return this.spaces;
    }
}
