package network.client;

import javafx.print.PageLayout;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.interfaces.Gainable;
import logic.player.Player;
import network.ResponseCode;
import network.server.RemotePlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IBM on 06/06/2017.
 */
public interface RMIClientInterface extends Remote {
    void sendMessage2(String string) throws RemoteException;
    void notifyRequestHandleOutcome(ResponseCode requestHandleOutcome )throws RemoteException;
    boolean dealWithVatican(int periodNumber)throws RemoteException;
    void updateView(Board board, Collection<Player> players);
    Gainable[] selectCouncilFavour(int numberOfFavours);
    ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces);


}
