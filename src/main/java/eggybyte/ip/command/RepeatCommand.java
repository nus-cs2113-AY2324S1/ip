package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * [OBSOLETE]
 * Command for Reapeating Undefined Commands.
 */
public class RepeatCommand extends Command {

    public static final String COMMAND_WORD = "repeat";
    protected static final int validArgumentAmount = 0;
    private String input;

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
    public RepeatCommand(String input, String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        this.input = input;
        checkArguments(arguments);
    }

    @Override
    public String customFunction() {
        return "";
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(" " + input);
    }
}
