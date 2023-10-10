package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;

/**
 * Commands for Listing All Existing Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    protected static final int validArgumentAmount = 0;

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
    public ListCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
    }

    @Override
    public String customFunction() {
        if (runningState.tasks.size() == 0) {
            return "The list is empty!";
        }

        String result = taskToString(0);
        for (int i = 1; i < runningState.tasks.size(); i++) {
            result += "\n" + taskToString(i);
        }
        return result;
    }

    /**
     * Convert task to string
     *
     * @param tasks To make clear which tasks is going to be converted.
     * @param index To make clear which element of the tasks is going to be
     *              converted, and the index is to be shown in the front.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    private String taskToString(int index) {
        Task task = runningState.tasks.get(index);
        return " " + (index + 1) + "." + task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "Here are the tasks in your list:\n"
                        + content);
    }
}
