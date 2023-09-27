package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * Terminates the program.
 */
public class RepeatCommand extends Command {

    public static final String COMMAND_WORD = "repeat";
    protected static final int validArgumentAmount = 0;
    private String input;

    public RepeatCommand(String input, String[] arguments) throws Exception {
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
        return new CommandResult(" " +input);
    }
}
