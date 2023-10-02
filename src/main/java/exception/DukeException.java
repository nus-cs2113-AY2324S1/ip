package exception;

/*
 * A wrapper class for the Exception class that wraps the error message.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /*
     * @return the error message wrapped in custom text.
     */
    public String getErrorMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
