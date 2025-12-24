package src.com.airtribe.learntrack.entity;

public enum BatchType {
    ONLINE(1),
    OFFLINE(2);

    private final int code;

    BatchType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static BatchType fromInput(int input) {
        for (BatchType type : values()) {
            if (type.code == input) {
                return type;
            }
        }
        // default to first value
        return values()[0];
    }
}
