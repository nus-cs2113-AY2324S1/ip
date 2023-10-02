package duke.exceptions;

/**
 * Represents an exception when the input is not a valid command.
 */
public class InvalidCommandException extends Exception {

    /**
     * Notifies user when the input is not a valid command.
     */
    public void printErrorMessage() {
        System.out.println("Oops, seems like I don't know this command. Please provide a valid command!");
    }
}
