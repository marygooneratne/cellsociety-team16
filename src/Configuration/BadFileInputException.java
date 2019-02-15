package Configuration;
/*
@author - Hyunjae Lee
This is a class for an exception that is thrown if a wrong type of file is inputted into the parser.
 */
public class BadFileInputException extends Exception {
    String message;
    /*
    @param - msg
    Constructor that sets the exception message
     */
    public BadFileInputException(String msg){
        super(msg);
        message=msg;
    }
    /*
    Gets the exception message to be displayed
     */
    public String getExceptionMsg(){
        return message;
    }
}
