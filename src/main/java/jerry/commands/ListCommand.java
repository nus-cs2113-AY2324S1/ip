package jerry.commands;

import jerry.task.Task;
import jerry.task.TaskList;

import java.util.List;


/**
 * Lists all tasks in the tasklist to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the tasklist as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<Task> allTasks = taskList.getAllTasks();
        return new CommandResult(getMessageForPersonListShownSummary(allTasks), allTasks);
    }
}
