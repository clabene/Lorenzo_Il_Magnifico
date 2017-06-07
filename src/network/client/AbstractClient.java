package network.client;

/**
 * Created by IBM on 06/06/2017.
 */
public abstract class AbstractClient {

    private ClientInterface clientController;

    public void setClientController(ClientInterface clientController) {
        this.clientController = clientController;
    }

    public ClientInterface getClientController() {
        return this.clientController;
    }

    /*
    * Connects to the right server according to the preferred network type
    * */
    public abstract void connect() ;

    /*
    * Client sends request to be logged in with a specific name
    * */
    public abstract void tryToLogIn();

    /*
    * Client sends request to join a room
    * */
    public abstract void tryToJoinGame();

    /*
    * Client sends request to start a game in a new room. Can specify the number of player that can join the game.
    * */
    public abstract void tryToCreateRoom();


    /*
    * Client selects a family member from theirs
    * */
    public abstract void selectFamilyMember();

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
    * This method is triggered by the server side.
    * */
    public abstract void dealWithVatican();
    /*
    * Client decides what bonus they will get form a council favour.
    * This method is triggered by the server side.
    * */
    public abstract void selectCouncilFavour();
    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves();

    /*
    * Client leaves the game
    * */
    public abstract void leaveGame();


}
