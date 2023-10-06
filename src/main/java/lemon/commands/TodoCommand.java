package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.task.Todo;
import lemon.ui.UI;

/**
 * Represents a command to add a task with only the task description.
 * This command creates a new Todo object and adds it to task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task to the task list.
     * Creates a new Todo object, adds it to the task list,
     * displays the task that is added,
     * and saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Todo todo = new Todo(description, false);
        tasks.addTask(todo);
        ui.displayAddedTask(todo, tasks);
        storage.save(tasks.getTasks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
