package MySun.data.exception;

/**
 * Warn invalid command.
 */
public class InvalidActionException extends SunException {
    public InvalidActionException (String message){
        super(message);
    }
}
