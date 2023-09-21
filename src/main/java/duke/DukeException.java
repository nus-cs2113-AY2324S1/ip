package duke;

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