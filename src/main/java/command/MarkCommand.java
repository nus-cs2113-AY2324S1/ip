package command;

import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int taskId;

    /**
     * Constructor
     *
     * @param taskId id of the task to be marked as done
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes by calling the mark method in TaskList
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.mark(this.taskId);
        Ui.printMarkMessage(taskList.getTask(taskId));
    }
}
