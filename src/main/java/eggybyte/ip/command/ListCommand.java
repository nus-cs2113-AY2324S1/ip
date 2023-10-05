package eggybyte.ip.command;

import eggybyte.ip.data.task.Task;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    protected static final int validArgumentAmount = 0;

    public ListCommand(String[] arguments) throws Exception {
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
