package exceptions;

/**
 * Represents an exception that is related to unrecognised commands
 *
 * All input other than todo, deadline, event, mark, unmark, delete, list, find, bye
 * are considered as unrecognised commands
 */
public class InvalidCommandException extends ListWhisperExceptions {

    /**
     * Constructor
     *
     * @param errorMessage to be printed out when it is thrown
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
