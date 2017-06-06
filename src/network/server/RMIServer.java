package network.server;

import network.client.RMIClientInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIServer extends AbstractServer  implements RMIServerInterface{



    @Override
    public void startServer(int port) {
        System.out.println("RMI Server started");


        try{

            Registry reg;
            try{
                reg = LocateRegistry.createRegistry(port);
                System.out.println("java RMI registry created");
            }catch (Exception e){
                System.out.println("Using existing registry");
                reg = LocateRegistry.getRegistry();
            }

            reg.rebind("RMIServerInterface", this);
            UnicastRemoteObject.exportObject(this, port);
            System.out.println("ooooooooooooooooooooooo");



        }catch (RemoteException e){
            e.printStackTrace();
        }


    }

    @Override
    public void sendMessage(String string, RMIClientInterface c) throws RemoteException {
        System.out.println("chiamato metodo di server" +string);
        c.sendMessage2(string);

    }



}
