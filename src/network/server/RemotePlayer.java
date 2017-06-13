package network.server;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class RemotePlayer extends Player{

    private GameRoom gameRoom;

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
    public abstract void dealWithVatican(ExcommunicationTassel tassel);

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
    * This method is triggered by the server side (see PlayExtraActionPhaseEffect in package cardEffects)
    * */
    //todo sending action spaces id's may be a better solution
    public abstract ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces);

    /*
    * Notify client of the result of the processing of their previous request
    * */
    public abstract void notifyRequestHandleOutcome(ResponseCode responseCode);

    /*
    * Notify client of new board and new player's data after an action
    * */
    public abstract <P extends Player > void updateView(Board board, Collection<P> players);


}
