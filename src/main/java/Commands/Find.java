package Commands;

import Exceptions.KenException;
import Tasks.TaskList;

/**
 * Represents a command to find tasks in the TaskList that matches a specified keyword.
 */
public class Find extends Command {
    private final String keyword;

    /**
     * Constructor of "Find" command with the specified keyword for searching tasks.
     *
     * @param keyword The keyword to search for within the TaskList.
     */
    public Find(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the "Find" command by searching for tasks that contain the specified keyword in their description.
     * The matching tasks are then displayed to users.
     *
     * @param list The task list on which the command should operate.
     */
    @Override
    public void run(TaskList list) {
        list.getTasks(keyword);
    }
}
