package Exceptions;

/**
 * Exception thrown when the task cannot be found
 */
public class CSGPTMissingTaskException extends CSGPTException {
    /**
     * Constructor for CSGPTMissingTaskException
     */
    public CSGPTMissingTaskException() {
        super("You have no such task, mortal.");
    }
}
