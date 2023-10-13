package exceptions;

/**
 * Represent an exception that is related to error in the task description format inputted by the user
 */
public class DescriptionFormatException extends ListWhisperExceptions {

    /**
     * Constructor
     *
     * @param errorMessage to be printed out when it is thrown
     */
    public DescriptionFormatException(String errorMessage) {
        super(errorMessage);
    }
}
