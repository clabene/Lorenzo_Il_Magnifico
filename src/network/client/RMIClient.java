package network.client;

import network.server.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by IBM on 06/06/2017.
 */
public class RMIClient extends AbstractClient implements RMIClientInterface{
    private String ipAddress;
    private int port;

    public RMIClient(int port){
        this.ipAddress = "127.0.0.1";
        this.port = port;
    }

    @Override
    public void connect() {
        //connect to RMIServer
        try{

            Registry registry = LocateRegistry.getRegistry(ipAddress, port);
            RMIServerInterface obj = (RMIServerInterface) registry.lookup("RMIServerInterface");

        }
        catch (RemoteException e) {}

        catch (NotBoundException e) {}
    }
}
