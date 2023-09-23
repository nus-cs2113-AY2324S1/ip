package eggybyte.ip.command;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the
     * execution result
     */
    public final String result;
    public final Exception exception;

    public enum State {
        success, fail
    }

    public final State state;

    public CommandResult(String result) {
        this(State.success, result, null);
    }

    public CommandResult(Exception exception) {
        this(State.success, exception.toString(), exception);
    }

    public CommandResult(State state, String result, Exception exception) {
        this.result = result;
        this.state = state;
        this.exception = exception;
    }
}
