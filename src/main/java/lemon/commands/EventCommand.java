package lemon.commands;

import lemon.file.Storage;
import lemon.task.Event;
import lemon.task.TaskList;
import lemon.ui.UI;

/**
 * Represents a command to add a task with a start and end date/time.
 * This command creates a new Event object and adds it to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String description;
    private final String start;
    private final String end;

    /**
     * Constructs a EventCommand with the specified description, start and end date/time.
     *
     * @param description Description of the event task.
     * @param start Start date/time of the event task.
     * @param end End date/time of the event task.
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add a event task to the task list.
     * Creates a new Event object, adds it to the task list,
     * displays the task that is added,
     * and saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Event event = new Event(description, start, end, false);
        tasks.addTask(event);
        ui.displayAddedTask(event, tasks);
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
