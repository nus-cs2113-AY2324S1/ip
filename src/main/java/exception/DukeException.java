package exception;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
