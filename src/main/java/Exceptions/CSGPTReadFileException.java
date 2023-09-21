package Exceptions;

/**
 * Exception thrown when there is an error in reading the file
 */
public class CSGPTReadFileException extends CSGPTException{
    /**
     * Constructor for CSGPTReadFileException
     */
    public CSGPTReadFileException() {
        super("Error reading file");
    }
}
