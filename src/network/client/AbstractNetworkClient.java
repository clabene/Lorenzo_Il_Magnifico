package network.client;

import javafx.print.PageLayout;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Plank;
import logic.player.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by IBM on 06/06/2017.
 */
public abstract class AbstractNetworkClient {

    /*
    * Here client sends requests
    * */

    private ClientInterface clientController;

    public AbstractNetworkClient(ClientInterface clientController){
        this.clientController = clientController;
    }
    //private String id = UUID.randomUUID().toString();
    //private String name; //set this in Client class


/*
    public String getName() {
        return name;
    }
*/

    public String getId(){
        return getClientController().getId();
    }

    public ClientInterface getClientController() {
        return this.clientController;
    }

    /*
    * Connects to the right server according to the preferred network type
    * */
    public abstract void connect() ;

    /*
    * Client sends request to be logged in with a specific name and id
    * Id is to be the same id of the RemotePlayer associated with this client
    * */
    public abstract void tryToLogIn();

    /*
    * Client sends request to join a room
    * */
    public abstract void tryToJoinGame();

    /*
    * Client sends request to start a game in a new room. Can specify the number of player that can join the game.
    * */
    public abstract void tryToCreateRoom(int numberOfPlayers);

    /*
    * Client selects a family member from theirs
    * */
    public abstract void selectFamilyMember(FamilyMember familyMember);

    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves(int quantity);

    /*
    * Client selects an action space from the board
    * */
    public abstract void selectActionSpace(String actionSpaceId);

    /*
    * Client decides if they want to support the Vatican or not.
    * This method is triggered by the server side.
    * */
    //public abstract void dealWithVatican();

    /*
    * Client decides what bonus they will get form a council favour.
    * This method is triggered by the server side.
    * */
    //public abstract void selectCouncilFavour(int numberOfFavours);

    /*
    * Send an ArrayList of action spaces to the client
    * Client has to pick one of those
    * This method is triggered by the server side (see PlayExtraActionPhaseEffect in package cards.cardEffects)
    * */
    //public abstract void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces);

    /*
    * Client leaves the game
    * */
    public abstract void leaveGame();


}
