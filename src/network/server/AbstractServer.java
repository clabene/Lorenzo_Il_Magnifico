package network.server;

/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class AbstractServer {

    private ServerInterface serverController;

    public ServerInterface getServerController() {
        return serverController;
    }

    public void setServerController(ServerInterface serverController) {
        this.serverController = serverController;
    }

    public abstract void startServer(int port);

}
