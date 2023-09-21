package Exceptions;

/**
 * Exception thrown when the file is corrupted
 */
public class CSGPTFileCorruptedError extends CSGPTException{
    /**
     * Constructor for CSGPTFileCorruptedError
     * @param message Message to be printed
     */
    public CSGPTFileCorruptedError(String message) {
        super("Error! File corrupted. " + message);
    }
}
