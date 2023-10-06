package lemon.exception;

/**
 * Exception class specific to the Lemon chatbot.
 * This exception is thrown to indicate errors related to the chatbot.
 */
public class LemonException extends Exception {
    /**
     * Constructs a LemonException with the specified error message.
     *
     * @param message Error message associated with the exception.
     */
    public LemonException(String message) {
        super(message);
    }
}
