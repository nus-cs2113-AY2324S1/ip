package magpie.exceptions;

/**
 * Represents an Exception class for Magpie-related errors.<br>
 * Contains <code>errorMessage</code> to let users know the error. <i>E.g missing arguments</i>
 */
public class MagpieException extends Exception {

    private String errorMessage;

    /**
     * Constructs <code>errorMessage</code>.
     *
     * @param errorMessage errorMessage to be printed to user.
     */

    public MagpieException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns error message to be printed to user.
     *
     * @return Error Message of exception.
     */

    public String getErrorMessage() {
        return errorMessage;
    }

}
