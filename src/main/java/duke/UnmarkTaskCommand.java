package duke;

public class UnmarkTaskCommand implements Command {
    private int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTaskCount()) {
            tasks.markTaskAsUndone(taskIndex);
            storage.saveTasks(tasks.getTasks());
        } else {
            ui.showError("Invalid task index.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

