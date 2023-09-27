package eggybyte.ip.command;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    protected static final int validArgumentAmount = 0;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the PersonBook as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public ListCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
    }

    @Override
    public String customFunction() {
        if (runningState.tasks.size() == 0) {
            return "The list is empty!";
        }
        // String result = "1." + runningState.tasks.get(0).toString();
        String result = "1." + runningState.tasks.get(0).getDescription();
        for (int i = 1; i < runningState.tasks.size(); i++) {
            // result += "\n" + (i + 1) + "." + runningState.tasks.get(i).toString();
            result += "\n" + (i + 1) + "." + runningState.tasks.get(i).getDescription();
        }
        return result;
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                // "Here are the runningState.tasks in your list:\n"+
                content);
    }
}
