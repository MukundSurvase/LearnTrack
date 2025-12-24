package src.com.airtribe.learntrack.exception;

public class InvalidInput extends Exception{

    private String message;

    public InvalidInput(String message){

        super(message);
        this.message = message;
    }

    public InvalidInput(){
        message="Invalid Input !";
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String toString(){
        return message;
    }
}

