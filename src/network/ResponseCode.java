package network;

import java.io.Serializable;

/**
 * Created by IBM on 11/06/2017.
 */
public enum ResponseCode implements Serializable{
    OK(),
    LOGGED_IN(),
    GAME_JOINED(),
    ROOM_CREATED(),
    FAMILY_MEMBER_SELECTED(),
    ACTION_SPACE_SELECTED(),
    SLAVES_USED(),
    GENERIC_ERROR();

    ResponseCode(){}

}
