package network.server;


/**
 * Created by IBM on 06/06/2017.
 */
public class Server implements ServerInterface {

    private RMIServer rmiServer;
    private SocketServer socketServer;

    public Server(){


    }

    public void startServer(){
        rmiServer.startServer();
        socketServer.startServer();
    }

}
