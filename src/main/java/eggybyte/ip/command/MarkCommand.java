package eggybyte.ip.command;

import eggybyte.ip.data.task.Task;
import eggybyte.ip.data.task.Todo;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    protected static final int validArgumentAmount = 1;
    private final int index;

    public MarkCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        index = Integer.parseInt(arguments[0]) - 1;
    }

    @Override
    public String customFunction() {
        Task task = runningState.tasks.get(index);
        if (task instanceof Todo) {
            ((Todo) task).isDone = true;
        }
        return task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "Nice! I've marked this task as done:\n  "
                        + content);
    }
}
