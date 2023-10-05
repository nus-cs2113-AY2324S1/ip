package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Event;

/**
 * Commands for Adding Event Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    protected static final int validArgumentAmount = 3;

    /**
     * Creates a new Command.
     *
     * @param arguments The specified arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public EventCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Event(arguments[0], arguments[1], arguments[2]);
    }
}
