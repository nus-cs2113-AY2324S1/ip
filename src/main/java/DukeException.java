/**
 * Custom exception class for Duke application.
 * This exception is thrown when there is an issue or error specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
