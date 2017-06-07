package network.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pinos on 06/06/2017.
 */
public class SocketServer extends AbstractServer {

    @Override
    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("siamo alla frutta");
            printReadMessage(clientSocket);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void printReadMessage(Socket clientSocket) throws IOException{
        Scanner s = new Scanner(new BufferedInputStream(clientSocket.getInputStream()));
        System.out.println(s.nextLine());
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
