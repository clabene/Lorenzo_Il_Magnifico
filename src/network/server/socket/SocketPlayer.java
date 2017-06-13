package network.server.socket;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.gameStructure.GameRoom;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;
import network.server.RemotePlayer;
import network.server.ServerInterface;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketPlayer extends RemotePlayer implements Runnable {

    private ServerInterface serverController;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private ServerStreamHandler streamHandler;

    public SocketPlayer(ServerInterface serverController, Socket clientSocket) {
        this.serverController = serverController;
        try {
            input = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            output = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            output.flush();
        } catch (IOException e){
            System.out.println("Could not initialize streams");
        }
        streamHandler = new ServerStreamHandler(output, input, this);
    }

    @Override
    public void run(){
        try {
            while (true) {
                String requestCode = (String) input.readObject();
                streamHandler.respond(requestCode);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not have ServerStreamHandler started");
        } finally {
            closeStream(input);
            closeStream(output);
        }
    }
    private void closeStream(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            System.out.println("Problem during closing of streams");
        }
    }


    @Override
    public GameRoom getGameRoom() {
        return super.getGameRoom();
    }

    @Override
    public void setGameRoom(GameRoom gameRoom) {
        super.setGameRoom(gameRoom);
    }



    public void tryToLogInClient(String clientId) {
        setId(clientId);
        serverController.tryToLogIn(clientId, this);
    }

    public void tryToJoinGame() {
        serverController.tryToJoinGame(getId());
    }

    public void tryToJoinNewRoom(Integer NUMBER_OF_PLAYERS) {
        serverController.tryToCreateRoom(getId(), NUMBER_OF_PLAYERS);
    }


    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        getGameRoom().selectFamilyMember(familyMember, getId());
    }

    @Override
    public void selectActionSpace(String actionSpaceId) {
        getGameRoom().selectActionSpace(actionSpaceId, getId());
    }

    @Override
    public void useSlaves(int quantity) {
        getGameRoom().useSlaves(quantity, getId());
    }

    @Override
    public void dealWithVatican() {

    }

    @Override
    public void selectCouncilFavour(int numberOfFavours) {
        try {
            output.writeObject("SELECT_COUNCIL_FAVOUR");
            output.writeObject(numberOfFavours);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not ask for council favours");
        }
        try {
            Gainable[] favours = (Gainable[]) input.readObject();
            gain(favours);
            notifyRequestHandleOutcome(ResponseCode.OK);

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive council favour");
            notifyRequestHandleOutcome(ResponseCode.NOT_OK);
        }
    }


    @Override
    public ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        try {
            output.writeObject("SELECT_ACTION_SPACE_FOR_EXTRA_ACTION");
            output.writeObject(actionSpaces);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not ask for action space for extra action");
        }
        try {
            ActionSpace toReturn = (ActionSpace) input.readObject();
            notifyRequestHandleOutcome(ResponseCode.OK);
            return toReturn;
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive action space for extra action");
            notifyRequestHandleOutcome(ResponseCode.NOT_OK);
        }
        return null; //todo exception
    }

    @Override
    public void notifyRequestHandleOutcome(ResponseCode responseCode) {
        try {
            output.writeObject(responseCode);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send response code");
        }
    }

    @Override
    public <P extends Player> void updateView(Board board, Collection<P> players) {
        try {
            output.writeObject("UPDATE_VIEW");
            output.writeObject(board);
            output.writeObject(players);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not ask for council favours");
        }
    }
}
