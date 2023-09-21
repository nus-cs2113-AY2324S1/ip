package Exceptions;

/**
 * Exception thrown when there is an error in writing to the file
 */
public class CSGPTWriteFileException extends CSGPTException{
    /**
     * Constructor for CSGPTWriteFileException
     */
    public CSGPTWriteFileException() {
        super("Error writing to file");
    }
}
