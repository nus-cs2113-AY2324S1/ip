package duke.command;

/**
 * Wrapper for an exception throw within Duke
 */
public class DukeException extends  Exception {
    public DukeException(String message) {
        super(message);
    }
}
