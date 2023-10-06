package alice.commands;

import alice.tasks.Task;
import alice.enumeration.TaskStatus;
import alice.ui.Ui;

public class UpdateStatusCommand extends Command {
    private Task task;
    private TaskStatus status;

    public UpdateStatusCommand(TaskStatus status, Task task) {
        this.task = task;
        this.status = status;
    }

    @Override
    public void handleCommand() {
        switch (this.status) {
        case mark:
            task.markTask();
            break;
        case unmark:
            task.unmarkTask();
            break;
        default:
            Ui.printOneTabMessage("Status of task was not changed...");
        }
    }
}
