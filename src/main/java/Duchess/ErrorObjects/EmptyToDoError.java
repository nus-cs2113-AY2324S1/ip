package Duchess.ErrorObjects;
import Duchess.TaskObjects.ToDo;

public class EmptyToDoError extends IncompleteTaskError {
    public EmptyToDoError(String message, ToDo task) {
        super(message, task);
    }


    
}
