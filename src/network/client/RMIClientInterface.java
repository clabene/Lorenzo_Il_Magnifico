package network.client;

import network.ResponseCode;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by IBM on 06/06/2017.
 */
public interface RMIClientInterface extends Remote {
    void sendMessage2(String string) throws RemoteException;
    void notifyRequestHandleOutcome(ResponseCode requestHandleOutcome )throws RemoteException;
}
