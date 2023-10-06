package lemon.commands;

import lemon.file.Storage;
import lemon.task.Deadline;
import lemon.task.TaskList;
import lemon.ui.UI;

/**
 * Represents a command to add a task with a deadline.
 * This command creates a new Deadline object and adds it to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand with the specified description and due date/time.
     *
     * @param description Description of the deadline task.
     * @param by Due date/time of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * Creates a new Deadline object, adds it to the task list,
     * displays the task that is added,
     * and saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Deadline deadline = new Deadline(description, by, false);
        tasks.addTask(deadline);
        ui.displayAddedTask(deadline, tasks);
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
