package duke;

public class AddTodoCommand implements Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = new Todo(description); // Create a new Todo task
        tasks.addTask(newTodo); // Add the task to the task list
        ui.showTaskAdded(newTodo, tasks.getTaskCount()); // Display a message indicating the task has been added
        storage.saveTasks(tasks.getTasks()); // Save the updated task list to storage
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

