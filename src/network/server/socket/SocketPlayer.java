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
import network.server.rmi.RMIPlayer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketPlayer extends RemotePlayer implements Runnable {

    private transient ServerInterface serverController;

    private transient ObjectInputStream input;
    private transient ObjectOutputStream output;

    private transient ServerStreamHandler streamHandler;

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

                String requestCode = input.readObject().toString();
                System.out.println("-----------------------" + requestCode);
                streamHandler.respond(requestCode);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not have ServerStreamHandler call respond");

            e.printStackTrace();
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
    public void dealWithVatican(int periodNumber) {
        try {
            output.writeObject("DEAL_WITH_VATICAN");
            output.writeObject(periodNumber);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not ask for Vatican decision");
        }
        try {
            boolean notSupporting = (Boolean) input.readObject();
            System.out.println("..................................................player received this vativan decicision: "+notSupporting);
            setExcommunications(periodNumber, notSupporting);
            getGameRoom().takeExcommunication(this, notSupporting);
            notifyRequestHandleOutcome(ResponseCode.OK);

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive council favour");
            notifyRequestHandleOutcome(ResponseCode.GENERIC_ERROR);
        }
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
            notifyRequestHandleOutcome(ResponseCode.GENERIC_ERROR);
        }
    }


    @Override
    public void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        try {
            output.reset();
            output.writeObject("SELECT_ACTION_SPACE_FOR_EXTRA_ACTION");
            output.writeObject(actionSpaces);
            output.flush();
        } catch (IOException e){
            System.out.println("Could not ask for action space for extra action");
        }
        try {
            ActionSpace actionSpace = (ActionSpace) input.readObject();
            getGameRoom().doExtraAction(this, actionSpace);
            notifyRequestHandleOutcome(ResponseCode.OK);

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Could not receive action space for extra action");
            notifyRequestHandleOutcome(ResponseCode.GENERIC_ERROR);
        }
    }

    @Override
    public void notifyRequestHandleOutcome(ResponseCode responseCode) {
        try {
            //first write string
            output.writeObject("RESPONSE_CODE");
            output.writeObject(responseCode);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send response code");
        }
    }

    @Override
    public <P extends Player> void updateView(Board board, Collection<P> players) {
        try {
            output.reset();
            output.writeObject("UPDATE_VIEW");
            output.writeObject(board);

            ArrayList<Player> players1 = new ArrayList<>();
            players1.addAll(players);

            System.out.println();

            output.writeObject(players1);

            output.flush();
        } catch (IOException e){
            System.out.println("Could not update view");
            e.printStackTrace();
        }
    }

    @Override
    public void leaveGame() {
        getGameRoom().leaveGame(getId());

    }
}
