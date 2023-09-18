package Duchess.ErrorObjects;


public class UnrecognisedCommandError extends DuchessError {
    public UnrecognisedCommandError(String message) {
        super(message);
    }
    
    public void HandleError() {
        System.out.println("Error: " + this.getMessage());
    }
}
