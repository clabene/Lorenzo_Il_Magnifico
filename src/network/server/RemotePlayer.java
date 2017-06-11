package network.server;

import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;


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

    public RemotePlayer getRemotePlayer(){
        return this;
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
    public abstract void dealWithVatican();

    /*
    * Server sends request to client to decide which bonus they will get form corresponding player's council favours.
    * */
    public abstract void selectCouncilFavour();

    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves(FamilyMember familyMember, int quantity);

    /*
    * Send an ArrayList of action spaces to the client
    * Client has to pick one of those
    * This method is triggered by the server side (see PlayExtraActionPhaseEffect in package cards.cardEffects)
    * */
    public abstract void selectActionSpaceForExtraAction();

    public abstract void notifyRequestHandleOutcome(ResponseCode responseCode);


}
