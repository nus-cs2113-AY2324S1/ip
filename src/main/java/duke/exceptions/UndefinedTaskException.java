package duke.exceptions;

/**
 * An exception in case that the user types invalid command
 */
public class UndefinedTaskException extends DukeException{
    public UndefinedTaskException(String message) {
        super(message);
    }
}
