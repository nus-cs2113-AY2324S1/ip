package command;

import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Constructor
     *
     * @param taskId id of the task to be unmarked
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes by calling the unmark method in taskList
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmark(this.taskId);
        Ui.printUnmarkMessage(taskList.getTask(taskId));
    }
}
