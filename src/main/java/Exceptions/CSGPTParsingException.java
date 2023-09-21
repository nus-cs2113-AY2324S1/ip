package Exceptions;

/**
 * Exception thrown when there is an error in parsing
 */
public class CSGPTParsingException extends CSGPTException {
    /**
     * Constructor for CSGPTParsingException
     * @param message Message to be printed
     */
    public CSGPTParsingException(String message) {
        super(message);
    }
}
