package network.client;

import java.util.Scanner;

/**
 * Created by IBM on 06/06/2017.
 */
public class Client implements ClientInterface {

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;
    private int port;

    private AbstractClient abstractClient;

    private int selectNetworkType(){
        System.out.println("What network is preferred?");
        System.out.println("1 : RMI");
        System.out.println("2 : Socket");
        Scanner in = new Scanner(System.in);
        return in.nextInt();

    }

    public void setNetworkType(){
        if(selectNetworkType() == 1) abstractClient = new RMIClient();
        if(selectNetworkType() == 2) abstractClient = new SocketClient(SOCKET_PORT);
    }

    public static void main(String[] args){
        Client client = new Client();
        client.setNetworkType();
    }


}
