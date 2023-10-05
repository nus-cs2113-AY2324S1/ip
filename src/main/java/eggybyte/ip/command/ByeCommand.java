package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * Commands for Terminating the Program.
 * 
 * @see #COMMAND_WORD
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    protected static final int validArgumentAmount = 0;

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
    public ByeCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
    }

    @Override
    public String customFunction() {
        runningState.exit();
        return "";
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(" Bye. Hope to see you again soon!");
    }
}
