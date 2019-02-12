package Configuration;

public class BadFileInputException extends Exception {
    String message;
    public BadFileInputException(String msg){
        super(msg);
        message=msg;
    }
    public String getExceptionMsg(){
        return message;
    }
}
