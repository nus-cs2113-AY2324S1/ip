package Exceptions;

public class CSGPTMissingTaskException extends CSGPTException {
    public CSGPTMissingTaskException() {
        super("You have no such task, mortal.");
    }
}
