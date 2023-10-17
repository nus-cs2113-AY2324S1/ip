package Commands;

import Tasks.TaskList;

/**
 * Represents a command to list tasks in the Barbie-themed task manager in a nice way.
 */
public class List extends Command {

    /**
     * Executes the "List" command by displaying the list of tasks to the user.
     *
     * @param list The task list on which the command should operate.
     */
    @Override
    public void run(TaskList list) {
        list.getTasks();
    }
}
