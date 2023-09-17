package Duchess.ErrorObjects;

public class IncompleteTaskError extends Exception{
    public IncompleteTaskError(String message){
        super(message);
    }
}
