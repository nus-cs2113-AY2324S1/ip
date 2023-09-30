package exception;

public class FrankException extends Exception {
    public FrankException(String message) {
        super("Frank says: " + message);
    }

    public FrankException(Throwable cause) {
        super(cause);
    }

    public FrankException(String message, Throwable cause){
        super(message, cause);
    }
}
