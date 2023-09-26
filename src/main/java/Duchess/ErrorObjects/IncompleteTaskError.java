package Duchess.ErrorObjects;

import Duchess.TaskObjects.Task;

public class IncompleteTaskError extends DuchessError{
    protected Task task;

    public IncompleteTaskError(String message, Task task){
        super(message);
        this.task = task;
    }


}
