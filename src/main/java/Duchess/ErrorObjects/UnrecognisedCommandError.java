package Duchess.ErrorObjects;

/**
 * Class to handle unrecognised command errors.
 */
public class UnrecognisedCommandError extends DuchessError {
    
    /**
     * Constructor for UnrecognisedCommandError.
     * @param message Error message.
     */
    public UnrecognisedCommandError(String message) {
        super(message);
    }
    
}
