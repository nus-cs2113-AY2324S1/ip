package duke;

public class DukeException extends Exception {

    private String errMsg;

    public DukeException(String errMsg) {
        this.errMsg = errMsg;
    }

    public DukeException(String errMsg, Throwable cause) {
        super(cause);
        this.errMsg = errMsg;
    }

    public DukeException(ErrorMessageType errMsgType) {
        switch (errMsgType) {
            case EMPTY_COMMAND:
                this.errMsg = "OOPS!!! A command cannot be empty.";
                break;
            case INVALID_INDICATOR:
                this.errMsg = "OOPS!!! Please enter a valid indicator.";
                break;
            case INVALID_DEADLINE:
                this.errMsg = "OOPS!!! Deadline command must be of form: deadline ___ /by ___.";
                break;
            case INVALID_EVENT:
                this.errMsg = "OOPS!!! Event command must be of form: event ___ /from ___ /to ___.";
                break;
            case INVALID_TASK_NUMBER:
                this.errMsg = "OOPS!!! Please enter a valid task number.";
                break;
            case INVALID_DESCRIPTION:
                this.errMsg = "OOPS!!! The description of a command cannot be empty.";
                break;
            case INVALID_ACTION_COMMAND:
                this.errMsg = "OOPS!!! Too many arguments.";
                break;
            default:
                this.errMsg = "OOPS!!! I don't know what that means.";
        }
    }

    @Override
    public String toString() {
        return String.format("%s", errMsg);
    }
}