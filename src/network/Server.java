package network;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by IBM on 03/06/2017.
 */
public class Server {

    private int port;

    public Server(int port){
        this.port = port;
    }

    public static void main(String[] args){
        Server server = new Server(9876);

        try {
            ServerSocket serverSocket = new ServerSocket(server.port);
            serverSocket.accept();

        } catch (IOException e){
            System.out.println("main, Server class\n"+e.getMessage());
        }
    }


}
