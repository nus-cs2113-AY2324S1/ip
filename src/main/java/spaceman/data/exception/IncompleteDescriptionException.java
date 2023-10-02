package spaceman.data.exception;

/**
 * Signals that the description of the task to be added is incomplete.
 */
public class IncompleteDescriptionException extends SpacemanException {
    public IncompleteDescriptionException (String message){
        super(message);
    }
}
