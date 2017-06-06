package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketServer extends AbstractServer {


    @Override
    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /*
    private void handleConnection(Socket clientSocket){
        while(true) {
            ExecutorService service = Executors.newCachedThreadPool();
            try {
                System.out.println("Connected");
                service.submit( /* runnable goes here * / );

            } catch (IOException e) {
                System.out.println("run, Acceptor class\n" + e.getMessage());
            }
        }
    }
    */



}
