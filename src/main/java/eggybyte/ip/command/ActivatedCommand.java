package eggybyte.ip.command;

import eggybyte.ip.data.Date;
import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.*;

import java.util.ArrayList;

/**
 * Commands for Searching Activated Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class ActivatedCommand extends Command {

    public static final String COMMAND_WORD = "activated";
    private static final int validArgumentAmount = 1;
    private final Date date;

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
    public ActivatedCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        date = new Date(arguments[0]);
    }

    @Override
    public String customFunction() {
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : runningState.tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).by.compareDate(date) != -1) {
                    filteredTasks.add(task);
                }
            }
            if (task instanceof Event) {
                if (((Event) task).from.compareDate(date) != 1 && ((Event) task).to.compareDate(date) != -1) {
                    filteredTasks.add(task);
                }
            }
        }

        if (filteredTasks.size() == 0) {
            return "There are no activated tasks!";
        }

        String result = taskToString(filteredTasks, 0);
        for (int i = 1; i < filteredTasks.size(); i++) {
            result += "\n" + taskToString(filteredTasks, i);
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
    private String taskToString(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        return " " + (index + 1) + "." + task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "Here are the tasks that are still activated on " + date.toString() + ":\n" + content);
    }
}
