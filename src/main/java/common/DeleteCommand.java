package common;

import listWhisper.task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.delete(taskId);
        Messages.printDeleteMessage(taskList, taskList.delete(taskId));
    }
}
