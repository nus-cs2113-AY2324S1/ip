package Duchess.ErrorObjects;

import Duchess.TaskObjects.Event;

/**
 * Class to handle incomplete event errors.
 */
public class IncompleteEventError extends IncompleteTaskError{
    
    /**
     * Constructor for IncompleteEventError.
     * @param message Error message.
     * @param task Task that is incomplete.
     */
    public IncompleteEventError(String message, Event task){
        super(message, task);
    }

}
