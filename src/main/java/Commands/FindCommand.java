package Commands;

import Cara.*;
import Tasks.Task;
import Tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list that match a specific keyword.
 */
public class FindCommand implements Command {
    private final String input;

    /**
     * Constructs a FindCommand with the given keyword to search for.
     *
     * @param input The keyword to search for in the task list.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the FindCommand by searching for tasks that match the keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager (not used in this command).
     * @throws CaraException If an error occurs during the task search.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws CaraException {
        TaskList.findTask(input, tasks);
    }

    /**
     * Checks if this command indicates program exit.
     *
     * @return False because this command does not indicate program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
