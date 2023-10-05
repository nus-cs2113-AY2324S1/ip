package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Deadline;

/**
 * Commands for Adding Deadling Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    protected static final int validArgumentAmount = 2;

    /**
     * Create a new Command.
     *
     * @param arguments The specified arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public DeadlineCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Deadline(arguments[0], arguments[1]);
    }
}
