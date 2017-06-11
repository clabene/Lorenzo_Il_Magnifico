package network.client.socket;

import logic.player.FamilyMember;
import network.ResponseCode;
import network.client.AbstractClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by IBM on 06/06/2017.
 */
public class SocketClient extends AbstractClient {

    private String ipAddress;
    private int port;
    private Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public SocketClient(int port){
        this.ipAddress = "127.0.0.1";
        this.port = port;
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            System.out.println("Could not connect ot the server");
        }
        initializeStreams();

        new SocketClientListener(input, this).start();
    }

    private void initializeStreams(){
        try {
            this.output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            output.flush();
            this.input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Could not initialize streams");
        }

    }


    private void notifyRequestHandleOutcome(){
        try {
            ResponseCode requestHandleOutcome = (ResponseCode) input.readObject();
            getClientController().showOutcome(requestHandleOutcome);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problems in notifying request output");
        }
    }

    @Override
    public void tryToLogIn() {
        try{
            output.writeObject("LOG_IN_REQUEST");
            output.writeObject(getId());
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send log in request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void tryToJoinGame() {
        try{
            output.writeObject("JOIN_GAME_REQUEST");
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send join game in request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void tryToCreateRoom(int numberOfPlayers) {
        try{
            output.writeObject("CREATE_NEW_ROOM_REQUEST");
            output.writeObject(numberOfPlayers); //check if boxing is necessary
            output.flush();
        } catch (IOException e){
            System.out.println("Could not send join game in request");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        try {
            output.writeObject("FAMILY_MEMBER_SELECTION_REQUEST");
            output.writeObject(familyMember);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void selectBoardArea() {

    }

    @Override
    public void useSlaves() {

    }

    @Override
    public void selectActionSpace() {

    }

    @Override
    public void dealWithVatican() {

    }

    @Override
    public void selectCouncilFavour() {
        ArrayList<Integer> favoursIndexes = new ArrayList<>();
        //todo ask client what council favours they want. Store correspondent indexes in the favoursIndexes
        try {
            output.writeObject(favoursIndexes);
        } catch (IOException e){
            System.out.println("Could not send council favours indexes");
        }
        notifyRequestHandleOutcome();
    }

    @Override
    public void selectActionSpaceForExtraAction() {

    }

    @Override
    public void leaveGame() {

    }
}
