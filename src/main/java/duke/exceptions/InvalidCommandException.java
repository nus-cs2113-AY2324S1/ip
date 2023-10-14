package duke.exceptions;

/**
 * Represents an exception when the input is not a valid command.
 */
public class InvalidCommandException extends Exception {

    /**
     * Provides error message for the case when the input is not a valid command.
     */
    public String getErrorMessage() {
        return "Oops, seems like I don't know this command. Please provide a valid command!";
    }
}
