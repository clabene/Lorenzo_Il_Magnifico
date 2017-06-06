package network.client;

import java.util.Scanner;

/**
 * Created by IBM on 06/06/2017.
 */
public class Client implements ClientInterface {

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    private AbstractClient myClient;

    private int selectNetworkType(){
        System.out.println("What network is preferred?");
        System.out.println("1 : RMI");
        System.out.println("other : Socket");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void setNetworkType(){
        if(selectNetworkType() == 1) myClient = new RMIClient(RMI_PORT);
        else  myClient = new SocketClient(SOCKET_PORT);
    }

    public AbstractClient getMyClient() {
        return myClient;
    }

    public static void main(String[] args){
        Client client = new Client();
        client.setNetworkType();
        client.getMyClient().connect();

        Scanner input = new Scanner(System.in);

        while(true){
            if(input.nextInt() == 5)
                System.out.println("ciao");
        }
    }


}
