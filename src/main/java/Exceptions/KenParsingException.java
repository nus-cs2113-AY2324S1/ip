package Exceptions;

/**
 * A custom exception class for handling parsing issues in the Barbie-themed task manager.
 * This exception is thrown when there is a problem parsing user input or commands.
 */
public class KenParsingException extends KenException {

    /**
     * Constructs a new instance of the KenParsingException class with the specified error message.
     *
     * @param message The error message that describes the parsing issue.
     */
    public KenParsingException(String message) {
        super(message);
    }
}
