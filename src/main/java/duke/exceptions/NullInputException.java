package duke.exceptions;

/**
 * An exception in case that the user types nothing
 */
public class NullInputException extends DukeException{
    public NullInputException(String message) {
        super(message);
    }
}
