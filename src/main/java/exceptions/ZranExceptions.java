package exceptions;

/**
 * Exception class for Zran application-specific exceptions.
 * Extends the standard Exception class.
 */
public class ZranExceptions extends Exception{
    public ZranExceptions(String message) {
        super(message);
    }
}
