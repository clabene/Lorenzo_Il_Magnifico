package network.server;

import logic.exceptions.LimitedValueOffRangeException;
import logic.gameStructure.GameRoom;
import network.ResponseCode;
import network.server.rmi.RMIServer;
import network.server.socket.SocketServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 06/06/2017.
 */
public class Server implements ServerInterface, Serializable {

    private RMIServer rmiServer = new RMIServer(this);
    private SocketServer socketServer = new SocketServer(this);

    private final Object MUTEX = new Object();

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    private HashMap<String, RemotePlayer> playersList = new HashMap<>(); //key: id

    private ArrayList<GameRoom> gameRooms = new ArrayList<>();

    public Server(){

    }

    private void startServer(){
        rmiServer.startServer(RMI_PORT);
        socketServer.startServer(SOCKET_PORT);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();

    }

    @Override
    public void tryToLogIn(String clientId, RemotePlayer player) {

        synchronized (MUTEX) {
            playersList.put(clientId, player);
        }


        getPlayer(clientId).notifyRequestHandleOutcome(ResponseCode.LOGGED_IN);
    }


    @Override
    public RemotePlayer getPlayer(String playerId){
        return playersList.get(playerId);
    }

    @Override
    public void tryToJoinGame(String playerId) {
        synchronized (MUTEX) {
            try {
                gameRooms.get(gameRooms.size() - 1).addPlayerToRoom(getPlayer(playerId));
                getPlayer(playerId).notifyRequestHandleOutcome(ResponseCode.GAME_JOINED);
            } catch (LimitedValueOffRangeException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Room not available");
                getPlayer(playerId).notifyRequestHandleOutcome(ResponseCode.GENERIC_ERROR);
            }
        }
    }

    @Override
    public void tryToCreateRoom(String playerId, int NUMBER_OF_PLAYERS ) {
        synchronized (MUTEX) {
            GameRoom gameRoom = new GameRoom(NUMBER_OF_PLAYERS);
            try {
                gameRoom.addPlayerToRoom(getPlayer(playerId));
                gameRooms.add(gameRoom);
                getPlayer(playerId).notifyRequestHandleOutcome(ResponseCode.ROOM_CREATED);
            } catch (LimitedValueOffRangeException e) {
                System.out.println("Could not add the player to the room");
                getPlayer(playerId).notifyRequestHandleOutcome(ResponseCode.GENERIC_ERROR);
            }
        }
    }

    @Override
    public void leaveGame(String id) {

    }
}
