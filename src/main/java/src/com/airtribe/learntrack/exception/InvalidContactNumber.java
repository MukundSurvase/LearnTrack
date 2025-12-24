package src.com.airtribe.learntrack.exception;

public class InvalidContactNumber extends Exception{

    private String message;

    public InvalidContactNumber(String message){

        super(message);
        this.message = message;
    }

    public InvalidContactNumber(){

        message = "Invalid Contact number !!";
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }
}
