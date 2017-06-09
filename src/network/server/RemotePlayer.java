package network.server;

import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;



/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class RemotePlayer extends Player{

    private GameRoom gameRoom;

    public RemotePlayer(String id){ //todo check if this work or if is useful at all!!
        super(id);
    }

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
    * Client selects an area from the board
    * */
    public abstract void selectBoardArea();

    /*
    * Client selects an action space from the board
    * */
    public abstract void selectActionSpace();

    /*
    * Client decides if they want to support the Vatican or not.
    * */
    public abstract void dealWithVatican();

    /*
    * Client decides what bonus they will get form a council favour.
    * */
    public abstract void selectCouncilFavour();

    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves();

    /*
    * Send an ArrayList of action spaces to the client
    * Client has to pick one of those
    * This method is triggered by the server side (see PlayExtraActionPhaseEffect in package cards.cardEffects)
    * */
    public abstract void selectActionSpaceForExtraAction();

    public abstract void notifyRequestHandleOutcome(String responseCode);

}
