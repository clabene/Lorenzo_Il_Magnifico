package network.client;

/**
 * Created by IBM on 06/06/2017.
 */
public class SocketClient extends AbstractClient {

    private String ipAddress;
    private int port;

    public SocketClient(int port){
        this.ipAddress = "127.0.0.1";
        this.port = port;
    }

    @Override
    public void connect() {
        //connect to SocketServer
    }





}
