package dawson.exception;

/**
 * Custom exception class for Dawson, used to handle and report errors 
 * specific to the Dawson application.
 */
public class DawsonException extends Exception {
    
    public DawsonException(String description) {
        super(description);
    }

}
