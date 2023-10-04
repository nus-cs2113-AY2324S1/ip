package Duchess.ErrorObjects;

import Duchess.TaskObjects.Task;

/**
 * Class to handle incomplete task errors.
 */
public class IncompleteTaskError extends DuchessError{
    protected Task task;

    /**
     * Constructor for IncompleteTaskError.
     * @param message Error message.
     * @param task Task that is incomplete.
     */
    public IncompleteTaskError(String message, Task task){
        super(message);
        this.task = task;
    }


}
