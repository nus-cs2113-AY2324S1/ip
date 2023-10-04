package Duchess.ErrorObjects;
import Duchess.TaskObjects.ToDo;

/**
 * Class to handle empty todo errors.
 */
public class EmptyToDoError extends IncompleteTaskError {
    
    /**
     * Constructor for EmptyToDoError.
     * @param message Error message.
     * @param task Task that is empty.
     */
    public EmptyToDoError(String message, ToDo task) {
        super(message, task);
    }


    
}
