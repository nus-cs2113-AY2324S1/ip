package MySun.data.exception;

/**
 * Signals that the date format provided by the user is invalid.
 */
public class InvalidDateFormatException extends SunException {
    public InvalidDateFormatException(String message){
        super(message);
    }
}
