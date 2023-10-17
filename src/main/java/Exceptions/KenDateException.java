package Exceptions;

/**
 * A custom exception class for handling date format issues in Event and Deadline tasks in the Barbie-themed task manager.
 * This exception is thrown when a date does not follow the expected format: dd/mm/yyyy HHmm.
 * It provides a specific error message to guide the user in formatting their date correctly.
 */
public class KenDateException extends KenException {

    /**
     * Constructors a new instance of the KenDateException class with predefined error message.
     * The error message instructs users to follow the expected date format
     */
    public KenDateException() {
        super("Uh-oh, I'm a little confused! Please make sure your date follows this style: dd/mm/yyyy HHmm. Thanks, doll!");
    }
}
