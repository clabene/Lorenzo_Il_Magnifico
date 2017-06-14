package network;

/**
 * Created by IBM on 11/06/2017.
 */
public enum ResponseCode {
    OK,
    LOGGED_IN,
    GAME_JOINED,
    ROOM_CREATED,
    FAMILY_MEMBER_SELECTED,
    ACTION_SPACE_SELECTED,
    SLAVES_USED,
    GENERIC_ERROR("Your request was not correctly handled");

    String message;

    ResponseCode(){}

    ResponseCode(String message){
    this.message = message;
    }

    public String getMessage() {
    return message;
    }
}
