package duke;

public class DeleteCommand implements Command {
    private int taskIndex;
    private Ui ui;  // Add this field to store the Ui object

    public DeleteCommand(int taskIndex, Ui ui) {  // Constructor that accepts Ui
        this.taskIndex = taskIndex;
        this.ui = ui;  // Set the Ui object
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTaskCount()) {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showTaskDeleted(removedTask, tasks.getTaskCount());  // Pass Ui object to showTaskDeleted
            storage.saveTasks(tasks.getTasks());
        } else {
            ui.showError("Invalid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
