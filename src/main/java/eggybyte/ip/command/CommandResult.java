package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * The Result after the Execution of the Command.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user.
     * Contains the execution result and exception if something unexpected happens.
     * State indicates whether the command is successfully executed.
     */
    public final String result;
    public final Exception exception;

    public enum State {
        success, fail
    }

    public final State state;

    /**
     * Creating a new Command Result in a simplified way.
     * It's used when the command is successfully executed.
     */
    public CommandResult(String result) {
        this(State.success, result, null);
    }

    /**
     * Creating a new Command Result in a simplified way.
     * It's used when something unexpected happens while the command is executed.
     */
    public CommandResult(Exception exception) {
        this(State.success, exception.toString(), exception);
    }

    /**
     * Creating a new Command Result with full arguments.
     * It's used for a complete constuction.
     */
    public CommandResult(State state, String result, Exception exception) {
        this.result = result;
        this.state = state;
        this.exception = exception;
    }
}
