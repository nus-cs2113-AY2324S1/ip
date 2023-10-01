package duke.exceptions;

/**
 * An exception in case that the user only types command without description
 */
public class NullDescriptionInputException extends DukeException{
    public NullDescriptionInputException(String message) {
        super(message);
    }
}
