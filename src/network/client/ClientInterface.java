package network.client;

import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;
import network.server.RemotePlayer;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IBM on 06/06/2017.
 */
public interface ClientInterface extends Serializable {

    String getId();

    void showOutcome(ResponseCode outcomeCode);

    void updateView(Board board, Collection<Player> players);

    ClientView getView();

    void logIn();

    void createNewRoom(int numberOfPlayers);

    void joinGame();

    void selectFamilyMember(FamilyMember familyMember);

    void selectActionSpace(String actionSpaceId);

    void useSlaves(int quantity);

    void leaveGame();

}
