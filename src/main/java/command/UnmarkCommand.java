package command;

import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskId;
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmark(this.taskId);
        Ui.printUnmarkMessage(taskList.getTask(taskId));
    }
}
