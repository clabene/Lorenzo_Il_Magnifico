package network.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by IBM on 10/06/2017.
 */
public class SocketClientListener extends Thread {

    private ObjectInputStream input;
    private SocketClient socketClient;

    public SocketClientListener(ObjectInputStream input, SocketClient socketClient){
        this.input = input;
        this.socketClient = socketClient;
    }

    //todo might wanna make an HashMap with the ':: thing' here too

    @Override
    public void run(){
        try {
            while (true) {
                String serverRequestCode = (String) input.readObject();
                if(serverRequestCode.equals("SELECT_COUNCIL_FAVOUR")) socketClient.selectCouncilFavour();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not have ServerStreamHandler started");
        }
    }

}
