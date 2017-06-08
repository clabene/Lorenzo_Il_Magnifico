package network.server;

import network.client.RMIClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Pinos on 06/06/2017.
 */
public interface RMIServerInterface  extends Remote{

    void sendMessage(String string, RMIClientInterface c) throws RemoteException;
}
