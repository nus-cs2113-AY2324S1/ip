package Duchess.ErrorObjects;

import Duchess.TaskObjects.Deadline;

/**
 * Class to handle incomplete deadline errors.
 */
public class IncompleteDeadlineError extends IncompleteTaskError{
    
    /**
     * Constructor for IncompleteDeadlineError.
     * @param message Error message.
     * @param task Task that is incomplete.
     */
    public IncompleteDeadlineError(String message, Deadline task){
        super(message, task);
    }

    
}
