package eggybyte.ip.command;

import eggybyte.ip.data.task.Task;
import eggybyte.ip.data.task.Todo;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    protected static final int validArgumentAmount = 1;
    private final int index;

    public UnmarkCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        index = Integer.parseInt(arguments[0]) - 1;
    }

    @Override
    public String customFunction() {
        Task task = runningState.tasks.get(index);
        if (task instanceof Todo) {
            ((Todo) task).isDone = false;
        }
        return task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "OK, I've marked this task as not done yet:\n  "
                        + content);
    }
}
