package Exceptions;

/**
 * Exception thrown when the task cannot be found
 */
public class CSGPTException extends Exception {
    /**
     * Constructor for CSGPTException
     * @param message Message to be printed
     */
    public CSGPTException(String message) {
        super(message);
    }
}
