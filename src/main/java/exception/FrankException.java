package exception;

public class FrankException extends Exception {
    /**
     * Specific user input related errors
     * @param message String message detailing the user's error
     */
    public FrankException(String message) {
        super("Frank says: " + message);
    }

    /**
     * Miscellaneous throwable causes i.e. IO
     * @param cause Throwable cause
     */
    public FrankException(Throwable cause) {
        super(cause);
    }

    /**
     * Addresses a miscellaneous throwable cause with a specific message
     * @param message Specific message
     * @param cause Miscellaneous throwable cause
     */
    public FrankException(String message, Throwable cause){
        super(message, cause);
    }
}
