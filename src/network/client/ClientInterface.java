package network.client;

import logic.board.Board;
import logic.player.Player;
import network.ResponseCode;
import network.server.RemotePlayer;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by IBM on 06/06/2017.
 */
public interface ClientInterface {

    void showOutcome(ResponseCode outcomeCode);

    void updateView(Board board, Collection<Player> players);

}
