package commands;

import exceptions.InvalidCommandException;
import tasks.Task;
import enumeration.TaskStatus;
import ui.Ui;

public class UpdateTaskStatus extends Command {
    Task task;
    TaskStatus status;

    public UpdateTaskStatus(TaskStatus status, Task task) {
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
