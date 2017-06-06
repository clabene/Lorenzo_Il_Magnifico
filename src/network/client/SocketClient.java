package network.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by IBM on 06/06/2017.
 */
public class SocketClient extends AbstractClient {

    private String ipAddress;
    private int port;
    private Socket socket;

    public SocketClient(int port){
        this.ipAddress = "127.0.0.1";
        this.port = port;
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
