package Exceptions;

/**
 * A custom exception class for handling missing task issues in the Barbie-themed task manager.
 * This exception is thrown when an operation attempts to access or manipulate a task that does not exist in the task manager.
 */
public class KenMissingTaskException extends KenException {

    /**
     * Constructs a new instance of the KenMissingTaskException class with a predefined error message.
     * The error message informs the user that the requested task cannot be found.
     */
    public KenMissingTaskException() {
        super("Sweetie, that's not on the Barbie agenda!");
    }
}
