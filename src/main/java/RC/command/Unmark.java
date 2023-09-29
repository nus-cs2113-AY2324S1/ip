package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class Unmark extends RCCommand {
    private String index;

    public Unmark(String index) {
        this.index = index;
    }

    /**
     * Unmarks a task as done based on its index.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The user interface for displaying messages.
     * @throws RCException If there is an issue unmarking a task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.unmarkTask(index, ui);
    }
}
