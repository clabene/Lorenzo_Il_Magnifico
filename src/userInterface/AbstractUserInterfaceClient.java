package userInterface;

import logic.player.FamilyMember;
import network.ResponseCode;
import network.client.ClientInterface;

import java.util.HashMap;

/**
 * Created by IBM on 14/06/2017.
 */
public abstract class AbstractUserInterfaceClient {

    private ClientInterface clientController;
    private HashMap<ResponseCode, uiInnerInterface> map = new HashMap<>();


    public AbstractUserInterfaceClient(ClientInterface clientController){
        this.clientController = clientController;
        fillMap();
    }

    public ClientInterface getClientController() {
        return clientController;
    }

    private void fillMap(){
        map.put(ResponseCode.LOGGED_IN, this::successfullyLoggedIn);
        map.put(ResponseCode.GAME_JOINED, this::successfullyJoinedGame);
        map.put(ResponseCode.ROOM_CREATED, this::successfullyCreatedRoom);
        map.put(ResponseCode.FAMILY_MEMBER_SELECTED, this::successfullySelectedFamilyMember);
        map.put(ResponseCode.ACTION_SPACE_SELECTED, this::successfullySelectedActionSpace);
        map.put(ResponseCode.SLAVES_USED, this::successfullyUsedSlaves);
        map.put(ResponseCode.GENERIC_ERROR, this::handleError);
    }


    protected abstract void successfullyLoggedIn();
    protected abstract void successfullyJoinedGame();
    protected abstract void successfullyCreatedRoom();
    protected abstract void successfullySelectedFamilyMember();
    protected abstract void successfullySelectedActionSpace();
    protected abstract void successfullyUsedSlaves();
    protected abstract void handleError();

    public abstract void updateView();


    protected void logIn(){
        getClientController().logIn();
    }
    protected void createNewRoom(int numberOfPlayers){
        getClientController().createNewRoom(numberOfPlayers);
    }
    protected void joinGame(){
        getClientController().joinGame();
    }
    protected void selectFamilyMember(FamilyMember familyMember){
        getClientController().selectFamilyMember(familyMember);
    }
    protected void selectActionSpace(String actionSpaceId){
        getClientController().selectActionSpace(actionSpaceId);
    }
    protected void useSlaves(int quantity){
        getClientController().useSlaves(quantity);
    }



    public void updateUi(ResponseCode rc) {
        uiInnerInterface handler = map.get(rc);
        if (handler != null) {
            handler.update();
        }
        else System.out.println("error");
    }

    @FunctionalInterface
    private interface uiInnerInterface {
        void update();
    }

}
