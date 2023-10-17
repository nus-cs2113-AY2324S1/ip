package duke;

public class MarkTaskCommand implements Command {
    private int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isValidTaskIndex(taskIndex, tasks)) {
            tasks.markTaskAsDone(taskIndex);
            ui.showTaskMarkedDone(tasks.getTask(taskIndex));
            storage.saveTasks(tasks.getTasks());
        } else {
            ui.showError("Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    // Checks if the task index is valid
    private boolean isValidTaskIndex(int index, TaskList tasks) {
        return index >= 0 && index < tasks.getTaskCount();
    }
}

