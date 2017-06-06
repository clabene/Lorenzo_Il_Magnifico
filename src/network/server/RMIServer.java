package network.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIServer extends AbstractServer implements RMIServerInterface{


    @Override
    public void startServer(int port) {
        System.out.println("RMI Server started");
        RMIServer obj = new RMIServer();

        try{
            RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry reg;

            try{
                reg = LocateRegistry.createRegistry(port);
                System.out.println("java RMI registry created");
            }catch (Exception e){
                System.out.println("Using existing registry");
                reg = LocateRegistry.getRegistry();
            }

            reg.rebind("Server", stub);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }



}
