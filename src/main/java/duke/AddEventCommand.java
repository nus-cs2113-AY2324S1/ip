package duke;

public class AddEventCommand implements Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newEvent = new Event(description, from, to); // Create a new Event task
        tasks.addTask(newEvent); // Add the task to the task list
        ui.showTaskAdded(newEvent, tasks.getTaskCount()); // Display a message indicating the task has been added
        storage.saveTasks(tasks.getTasks()); // Save the updated task list to storage
    }

    @Override
    public boolean isExit() {
        return false; // Events don't trigger program exit
    }
}

