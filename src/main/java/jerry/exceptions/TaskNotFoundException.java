package jerry.exceptions;

/**
 * Signals that the task has not been found in the tasklist.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
