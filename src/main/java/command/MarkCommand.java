package command;

import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int taskId;
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.mark(this.taskId);
        Ui.printMarkMessage(taskList.getTask(taskId));
    }
}
