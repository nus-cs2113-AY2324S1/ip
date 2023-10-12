package command;

import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructor
     *
     * @param taskId the id of the task to be deleted
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the command by calling delete method in Tasklist
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.delete(taskId);
        Ui.printDeleteMessage(taskList, taskList.delete(taskId));
    }
}
