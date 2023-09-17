package Duchess.ErrorObjects;

public class UnrecognisedCommandError extends Exception {
    public UnrecognisedCommandError(String message) {
        super(message);
    }
}
