package Duchess.ErrorObjects;

import Duchess.TaskObjects.Task;

public class IncompleteTaskError extends DuchessError{

    private Task task;

    public IncompleteTaskError(String message, Task task){
        super(message);
        this.task = task;
    }

    public void HandleError(){
        System.out.println("Error: " + this.getMessage());
    }
}
