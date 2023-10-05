package eggybyte.ip.command;

import eggybyte.ip.data.Date;
import eggybyte.ip.data.task.*;

import java.util.ArrayList;

/**
 * Lists all persons in the PersonBook to the user.
 */
public class ActivatedCommand extends Command {

    public static final String COMMAND_WORD = "activated";
    private static final int validArgumentAmount = 1;
    private final Date date;

    public ActivatedCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        date = new Date(arguments[0]);
    }

    @Override
    public String customFunction() {
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : runningState.tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).by.compareDate(date) != 1) {
                    filteredTasks.add(task);
                }
            }
            if (task instanceof Event) {
                if (((Event) task).to.compareDate(date) != -1 && ((Event) task).from.compareDate(date) != 1) {
                    filteredTasks.add(task);
                }
            }
        }

        if (filteredTasks.size() == 0) {
            return "There are no activated tasks!";
        }

        String result = taskToString(0);
        for (int i = 1; i < filteredTasks.size(); i++) {
            result += "\n" + taskToString(i);
        }
        return result;
    }

    private String taskToString(int index) {
        Task task = runningState.tasks.get(index);
        return (index + 1) + "." + task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "Here are the tasks that is still activated on " + date.toString() + ":\n" + content);
    }
}
