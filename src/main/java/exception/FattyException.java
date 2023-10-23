package exception;

/**
 * This is the exception class.
 * It contains the custom exception that is thrown by the chatbot.
 */

public class FattyException extends Exception{
    public FattyException(String errorMessage) {
        super(errorMessage);
    }
}
