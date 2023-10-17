package duke;

public class DeleteCommand implements Command {
    private int taskIndex;
    private Ui ui;

    public DeleteCommand(int taskIndex, Ui ui) {
        this.taskIndex = taskIndex;
        this.ui = ui;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTaskCount()) {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showTaskDeleted(removedTask, tasks.getTaskCount());  // Pass Ui object to showTaskDeleted
        } else {
            ui.showError("Invalid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
