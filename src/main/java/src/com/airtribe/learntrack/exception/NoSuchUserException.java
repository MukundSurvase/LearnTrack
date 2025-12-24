package src.com.airtribe.learntrack.exception;

public class NoSuchUserException extends Exception{
    private String message;
    public NoSuchUserException(){
        super("NO SUCH USER FOUND !");
        message = "NO SUCH USER FOUND !";
    }

    public NoSuchUserException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }
}
