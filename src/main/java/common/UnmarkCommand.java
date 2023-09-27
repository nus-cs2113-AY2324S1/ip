package common;

import listWhisper.task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskId;
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmark(this.taskId);
        Messages.printUnmarkMessage(taskList.getTask(taskId));
    }
}
