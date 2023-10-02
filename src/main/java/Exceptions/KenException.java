package Exceptions;

/**
 * The base class for custom exceptions in the Barbie-themed task manager.
 * This class extends the Java standard Exception class.
 */
public class KenException extends Exception {

    /**
     * Constructs a new instance of the KenException class with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public KenException(String message) {
        super(message);
    }
}
