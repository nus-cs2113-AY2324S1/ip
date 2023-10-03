package duke;

/**
 * DukeException is a custom exception class used to represent exceptions specific to the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
