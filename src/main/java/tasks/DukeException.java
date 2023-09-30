package tasks;

/**
 * Represents an exception that is thrown when an error occurs in the Duke application.
 */
public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
}
