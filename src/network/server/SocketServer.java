package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketServer extends AbstractServer {

    private ServerSocket serverSocket;

    public SocketServer(ServerInterface serverController){
        super(serverController);
    }

    @Override
    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            new SocketPlayerCreator().start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private class SocketPlayerCreator extends Thread{

        @Override
        public void run(){
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    SocketPlayer socketPlayer = new SocketPlayer(getServerController(), socket);
                    new Thread(socketPlayer).start();
                } catch (IOException e) {
                    System.out.println("Could not create socket player");
                    break;
                }
            }
        }

    }

}
