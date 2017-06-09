package network.server;

import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;

import java.io.*;
import java.net.Socket;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketPlayer extends RemotePlayer implements Runnable {

    private ServerInterface serverController;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private ServerSideStreamHandler streamHandler;

    public SocketPlayer(ServerInterface serverController, Socket clientSocket) {
        this.serverController = serverController;
        try {
            input = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            output = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            output.flush();
        } catch (IOException e){
            System.out.println("Could not initialize streams");
        }
        streamHandler = new ServerSideStreamHandler(output, input, this);
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

    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        getGameRoom().selectFamilyMember(familyMember, getId());
    }

    @Override
    public void selectBoardArea() {

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
    public void useSlaves() {

    }

    @Override
    public void selectActionSpaceForExtraAction(){

    }

    @Override
    public void notifyRequestHandleOutcome(String responseCode) {
        try {
            output.writeObject(responseCode);
            output.flush();
        } catch (IOException e) {
            System.out.println("Could not send response code");
        }
    }
}
