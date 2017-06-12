package network.client.socket;

import logic.board.Board;
import logic.player.Player;
import network.server.socket.ServerStreamHandler;
import network.server.socket.SocketPlayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        responseMap.put("UPDATE_VIEW", this::selectCouncilFavour);
        responseMap.put("SELECT_COUNCIL_FAVOUR", this::updateView);

    }

    public void respond(String s) {
        ResponseInnerInterface handler = responseMap.get(s);
        if (handler != null) {
            handler.operate();
        }
        else System.out.println("error");
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

    @FunctionalInterface
    private interface ResponseInnerInterface {
        void operate();
    }

}
