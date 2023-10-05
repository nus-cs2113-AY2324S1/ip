package eggybyte.ip.command;

import eggybyte.ip.data.Date;
import eggybyte.ip.data.task.*;

import java.util.ArrayList;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final int validArgumentAmount = 1;
    private final String partialDescription;

    public FindCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        partialDescription = arguments[0];
    }

    @Override
    public String customFunction() {
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : runningState.tasks) {
            if (task.description.contains(partialDescription)) {
                filteredTasks.add(task);
            }
        }

        if (filteredTasks.size() == 0) {
            return " There are no tasks with such partial description!";
        }

        String result = taskToString(filteredTasks, 0);
        for (int i = 1; i < filteredTasks.size(); i++) {
            result += "\n" + taskToString(filteredTasks, i);
        }
        return result;
    }

    private String taskToString(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        return " " + (index + 1) + "." + task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "Here are the tasks that have partial description with '" + partialDescription + "':\n" + content);
    }
}
