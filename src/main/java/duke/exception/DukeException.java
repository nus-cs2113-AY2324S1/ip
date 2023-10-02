package duke.exception;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException object with the given error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message){
       super(message);
    }

}