package network.client;

/**
 * Created by IBM on 06/06/2017.
 */
public abstract class AbstractClient {

    private ClientInterface clientController;

    public void setClientController(ClientInterface clientController) {
        this.clientController = clientController;
    }

    public ClientInterface getClientController() {
        return this.clientController;
    }

    public abstract void connect() ;


}
