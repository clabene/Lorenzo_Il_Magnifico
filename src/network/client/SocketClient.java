package network.client;

import logic.player.FamilyMember;

import java.io.*;
import java.net.Socket;

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
            String requestHandleOutcome = (String) input.readObject();
            getClientController().showOutcome(requestHandleOutcome);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problems in notifying request output");
        }
    }

    @Override
    public void tryToLogIn(String name, String id) {

    }

    @Override
    public void tryToJoinGame() {

    }

    @Override
    public void tryToCreateRoom() {

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

    }

    @Override
    public void selectActionSpaceForExtraAction() {

    }

    @Override
    public void leaveGame() {

    }
}
