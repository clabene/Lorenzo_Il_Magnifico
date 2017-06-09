package network.server;

import com.sun.applet2.AppletParameters;
import logic.exceptions.LimitedValueOffRangeException;
import logic.gameStructure.Game;
import logic.gameStructure.GameRoom;
import logic.player.Player;
import network.server.rmi.RMIServer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 06/06/2017.
 */
public class Server implements ServerInterface {

    private RMIServer rmiServer = new RMIServer();
    private SocketServer socketServer = new SocketServer(this);

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    private HashMap<String, RemotePlayer> playersList = new HashMap<>(); //key: id

    private ArrayList<GameRoom> gameRooms = new ArrayList<>();

    public Server(){

    }

    public void startServer(){
        rmiServer.startServer(RMI_PORT);
        socketServer.startServer(SOCKET_PORT);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();

    }

    @Override
    public void tryToLogIn(String clientId, RemotePlayer player) {
        playersList.put(clientId, player);
    }

    @Override
    public RemotePlayer getPlayer(String playerId){
        return playersList.get(playerId);
    }

    @Override
    public void tryToJoinGame(String playerId) {
        try{
            gameRooms.get(gameRooms.size()-1).addPlayerToRoom(getPlayer(playerId));
        } catch (LimitedValueOffRangeException e){
            System.out.println("Room not available");
            getPlayer(playerId).notifyRequestHandleOutcome("NOT_OK");
        }
    }

    @Override
    public void tryToCreateRoom(String playerId, int NUMBER_OF_PLAYERS ) {
        GameRoom gameRoom = new GameRoom(NUMBER_OF_PLAYERS);
        try {
            gameRoom.addPlayerToRoom(getPlayer(playerId));
        } catch (LimitedValueOffRangeException e){
            System.out.println("Could not add the player to the room");
            getPlayer(playerId).notifyRequestHandleOutcome("NOT_OK");
            return;
        }
        gameRooms.add(gameRoom);
    }

    @Override
    public void leaveGame() {

    }
}
