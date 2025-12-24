package src.com.airtribe.learntrack.exception;

public class InvalidEmailException extends Exception{

    private String message;

    public InvalidEmailException(String message){
        super(message);
        this.message = message;
    }

    public InvalidEmailException(){
        message = "Invalid Email Exception !!";
    }
    public String getMessage(){
        return "Invalid Email Exception !!";
    }

    public String toString(){
        return getMessage();
    }

}
