package Duchess.ErrorObjects;



/**
 * Generic parent class to handle errors with duchess.
 */
public class DuchessError extends Exception {

    /**
     * Constructor for DuchessError.
     * @param message Error message.
     */
    public DuchessError(String message) {
        super(message);
    }



}
