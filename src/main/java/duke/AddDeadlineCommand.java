package duke;

public class AddDeadlineCommand implements Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newDeadline = new Deadline(description, by); // Create a new Deadline task
        tasks.addTask(newDeadline); // Add the task to the task list
        ui.showTaskAdded(newDeadline, tasks.getTaskCount()); // Display a message indicating the task has been added
        storage.saveTasks(tasks.getTasks()); // Save the updated task list to storage
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

