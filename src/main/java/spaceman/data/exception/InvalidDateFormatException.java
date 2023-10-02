package spaceman.data.exception;

/**
 * Signals that the date format provided by the user is invalid.
 */
public class InvalidDateFormatException extends SpacemanException {
    public InvalidDateFormatException(String message){
        super(message);
    }
}
