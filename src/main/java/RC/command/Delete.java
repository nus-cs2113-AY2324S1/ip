package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents a command to delete a task.
 */
public class Delete extends RCCommand {
    private String index;
    
    public Delete(String index) {
        this.index = index;
    }

    /**
     * Deletes a task from the task list based in the index.
     *
     * @param taskList The task list containing tasks.
     * @param ui The user interface for displaying messages.
     * @throws RCException If there is an issue deleting a task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.delete(index, ui);
    }
}
