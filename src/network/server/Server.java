package network.server;


/**
 * Created by IBM on 06/06/2017.
 */
public class Server implements ServerInterface {

    private RMIServer rmiServer;
    private SocketServer socketServer;

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    public Server(){

    }

    public void startServer(){
        rmiServer.startServer(RMI_PORT);
        socketServer.startServer(SOCKET_PORT);
    }

}
