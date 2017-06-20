package network.server;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.gameStructure.GameRoom;
import logic.gameStructure.PeriodNumber;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class RemotePlayer extends Player implements Serializable {

    private transient GameRoom gameRoom;

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

    /*
    * Client selects a family member from theirs
    * */
    public abstract void selectFamilyMember(FamilyMember familyMember);

    /*
    * Client selects an action space from the board
    * */
    public abstract void selectActionSpace(String actionSpaceId);

    /*
    * Client decides if they want to support the Vatican or not.
    * */
    public abstract void dealWithVatican(int periodNumber);

    /*
    * Server sends request to client to decide which bonus they will get form corresponding player's council favours.
    * */
    public abstract void selectCouncilFavour(int numberOfFavours);

    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves(int quantity);

    /*
    * Client has to pick one of the action spaces sent as parameters
    * After that, game room's player has to use that action space for the extra action
    * This method is triggered by the server side (see PlayExtraActionPhaseEffect in package cardEffects)
    * */
    //todo sending action spaces id's may be a better solution
    public abstract void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces);

    /*
    * Notify client of the result of the processing of their previous request
    * */
    public abstract void notifyRequestHandleOutcome(ResponseCode responseCode);

    /*
    * Notify client of new board and new player's data after an action
    * */
    public abstract <P extends Player > void updateView(Board board, Collection<P> players);


}
