package src.com.airtribe.learntrack.exception;

public class UnAuthorizedException extends Exception {
    private String message;

    public UnAuthorizedException(){
        super("UnAuthorized User");
        message = "UnAuthorized User";
    }

    public UnAuthorizedException(String message){
        super(message);
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }
}

