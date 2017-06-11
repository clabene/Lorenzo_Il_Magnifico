package network;

/**
 * Created by IBM on 11/06/2017.
 */
public enum ResponseCode {
     OK("Your request was correctly handled"), NOT_OK("Your request was not correctly handled");

     String message;

     ResponseCode(String message){
         this.message = message;
     }

    public String getMessage() {
        return message;
    }
}
