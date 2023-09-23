package duke;

/**
 * DukeException is a custom exception class specific to the Duke application.
 * It is used to handle and propagate exceptions that occur during the execution of Duke's functionalities.
 */
public class DukeException extends Exception {

    private String errMsg;
    static String DIVIDER = "------------------------------------------------------------";

    public DukeException(String errMsg) {
        this.errMsg = errMsg;
    }

    public DukeException(String errMsg, Throwable cause) {
        super(cause);
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return String.format("%s", errMsg);
    }
}