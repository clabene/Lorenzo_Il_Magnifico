package network;

import java.io.Serializable;

/**
 * Created by IBM on 11/06/2017.
 */
public enum ResponseCode implements Serializable{
    OK(),
    GENERIC_ERROR(),

    LOGGED_IN(),
    UNABLE_TO_LOG_IN(),

    IT_IS_YOUR_TURN(),
    WAIT_YOUR_TURN(),


    GAME_JOINED(),
    UNABLE_TO_JOIN_GAME(),


    ROOM_CREATED(),
    UNABLE_TO_CREATE_ROOM(),


    FAMILY_MEMBER_SELECTED(),
    UNABLE_TO_SELECT_FAMILY_MEMBER(),


    ACTION_SPACE_SELECTED(),
    UNABLE_TO_SELECT_ACTION_SPACE(),

    SLAVES_USED(),
    SLAVES_NOT_AVAILABLE(),

    GAME_STARTED(),
    NOT_ENOUGH_PLAYERS(),

    EXCOMMUNICATION_TAKEN();


    ResponseCode(){}

}
