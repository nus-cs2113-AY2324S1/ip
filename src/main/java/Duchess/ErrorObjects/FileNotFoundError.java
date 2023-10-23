package Duchess.ErrorObjects;

/**
 * Class to handle file not found errors.
 */
public class FileNotFoundError extends DuchessError {
    
    /**
     * Constructor for FileNotFoundError.
     * @param message Error message.
     */
    public FileNotFoundError(String message) {
        super(message);
    }
    
}
