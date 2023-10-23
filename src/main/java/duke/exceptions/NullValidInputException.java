package duke.exceptions;

/**
 * An exception in case that the user types command that is not valid or supported in our program
 */
public class NullValidInputException extends DukeException {
    public NullValidInputException(String message) {
        super(message);
    }
}
