package src.com.airtribe.learntrack.exception;

public class BatchMaxSizeReachedException extends Exception{
    private String message;

    public BatchMaxSizeReachedException(){
        super();
        message = "can not enroll student as BATCH has already reached it's limit !";
    }

    public BatchMaxSizeReachedException(String message){
        super(message);
        this.message = message;
    }

    public String toString(){
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
