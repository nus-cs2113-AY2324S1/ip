package Duke;

/**
 * Represents a custom exception class specific to the Duke application.
 * This exception is used to handle errors and exceptional conditions within Duke.
 * For example the event where there is incorrect user input IO operations in Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor to create a new DukeException with the specified error message.
     * @param text The error message that describes the exception.
     */
    public DukeException(String text) {
        super(text);
    }
}
