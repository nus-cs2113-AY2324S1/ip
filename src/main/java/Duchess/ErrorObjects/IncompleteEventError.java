package Duchess.ErrorObjects;

import Duchess.TaskObjects.Event;

public class IncompleteEventError extends IncompleteTaskError{
    public IncompleteEventError(String message, Event task){
        super(message, task);
    }

    public void HandleError(){
        System.out.println("Error: " + this.getMessage());
    }
}
