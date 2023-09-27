package linguobot.command;
/**
 * The <code>LinguoBotException</code> class represents a custom exception used in the LinguoBot application.
 * It extends the standard Java `Exception` class and is used to handle specific application-related exceptions.
 */
public class LinguoBotException extends Exception {

    /**
     * Constructs a new <code>LinguoBotException</code> with the specified error message to be displayed.
     * @param message the error message associated with the exception.
     */
    public LinguoBotException(String message) {
        super(message);
    }
}
