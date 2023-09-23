package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    protected static final int validArgumentAmount = 0;

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
        return new CommandResult("Bye. Hope to see you again soon!");
    }
}
