package duke;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the ListCommand by displaying the list of tasks.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        TaskList.printList(tasks); // Display the list of tasks using TaskList.printList
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
