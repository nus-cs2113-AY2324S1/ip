package dude;

/**
 * The `DudeException` class represents a custom exception that can be thrown to handle
 * exceptional situations within the Dude application.
 */
public class DudeException extends Exception {

    /**
     * Constructs a new `DudeException` with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DudeException(String message) {
        super(message);
    }
}
