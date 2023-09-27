package spaceman.data.exception;

/**
 * Signals that the command executed by the user is invalid.
 */
public class InvalidActionException extends SpacemanException {
    public InvalidActionException (String message){
        super(message);
    }
}
