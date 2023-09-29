package RC.command;

import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents a command for listing all tasks in the task list.
 */
public class List extends RCCommand {
    private static final String MESSAGE_LIST_TASKS = "\tHere are the tasks in your list:";

    public List() {
    }

    /**
     * Lists out all the tasks currently in the task list.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessage(MESSAGE_LIST_TASKS);
        for (int i = 0; i < taskList.tasks.size(); i++) {
            ui.showMessage("\t" + (i + 1) + "." + taskList.tasks.get(i));
        }
    }
}
