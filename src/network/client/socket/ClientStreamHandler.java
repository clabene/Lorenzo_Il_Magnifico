package network.client.socket;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.player.Player;
import network.ResponseCode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 11/06/2017.
 */
public class ClientStreamHandler {


    private ObjectInputStream input;
    private SocketClient client;

    private HashMap<String, ResponseInnerInterface> responseMap = new HashMap<>();

    public ClientStreamHandler(ObjectInputStream input, SocketClient client){
        this.input = input;
        this.client = client;
        fillMap();
    }

    private void fillMap(){
        //todo use constants (make an Enum maybe) instead of String
        responseMap.put("RESPONSE_CODE", this::notifyResponseCode);
        responseMap.put("UPDATE_VIEW", this::updateView);
        responseMap.put("SELECT_COUNCIL_FAVOUR", this::selectCouncilFavour);
        responseMap.put("SELECT_ACTION_SPACE_FOR_EXTRA_ACTION", this::selectActionSpace);
        responseMap.put("DEAL_WITH_VATICAN", this::dealWithVatican);
    }

    private void notifyResponseCode(){
        try{
            ResponseCode responseCode = (ResponseCode) input.readObject();
            client.notifyRequestHandleOutcome(responseCode);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive response code");
        }
    }

    public void dealWithVatican(){
        try{
            Integer periodNumber = (Integer) input.readObject();
            client.dealWithVatican(periodNumber);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive period number");
        }
    }

    private void selectCouncilFavour(){
        try{
            Integer numberOfFavours = (Integer) input.readObject();
            client.selectCouncilFavour(numberOfFavours);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive council favour request");
        }
    }

    private void updateView(){
        try {
            Board board = (Board) input.readObject();
            ArrayList<Player> players = (ArrayList<Player>) input.readObject();
            client.getClientController().updateView(board, players);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not update view");
        }
    }

    private void selectActionSpace(){
        try {
            ArrayList<ActionSpace> actionSpaces = (ArrayList<ActionSpace>) input.readObject();
            client.selectActionSpaceForExtraAction(actionSpaces);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive action space for extra action request");
        }
    }

    public void respond(String s) {
        ResponseInnerInterface handler = responseMap.get(s);
        if (handler != null) {
            handler.operate();
        }
        else System.out.println("error");
    }

    @FunctionalInterface
    private interface ResponseInnerInterface {
        void operate();
    }

}
