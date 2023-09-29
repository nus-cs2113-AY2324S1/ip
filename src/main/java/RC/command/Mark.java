package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class Mark extends RCCommand {
    private String index;

    public Mark(String index) {
        this.index = index;
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The user interface for displaying messages.
     * @throws RCException If there is an issue marking a task as done.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.markAsDone(index, ui);
    }
}
