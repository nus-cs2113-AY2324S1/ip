package duke.exceptions;

/**
 * Superclass of all potential exceptions in our program
 */
public class DukeException extends Exception{
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}
