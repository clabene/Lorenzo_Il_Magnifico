package network.client;

import java.util.Scanner;

/**
 * Created by IBM on 06/06/2017.
 */
public class Client implements ClientInterface {

    private AbstractClient abstractClient;

    private int selectNetworkType(){
        System.out.println("What network is preferred?");
        System.out.println("1 : RMI");
        System.out.println("2 : Socket");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        return a;
    }

    public void selectNetworkSetting(){
        if(selectNetworkType() == 1) abstractClient = new RMIClient();
        if(selectNetworkType() == 2) abstractClient = new SocketClient();

    }

}
