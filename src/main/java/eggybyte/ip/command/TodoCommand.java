package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Todo;

/**
 * Commands for Adding Todo Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    protected static final int validArgumentAmount = 1;

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
    public TodoCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Todo(arguments[0]);
    }
}
