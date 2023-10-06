package jerry.commands;

import jerry.task.Task;
import jerry.task.Todo;
import jerry.task.Deadline;
import jerry.task.Event;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    public static final String TODO_COMMAND_WORD = "todo";
    public static final String DEADLINE_COMMAND_WORD = "deadline";
    public static final String EVENT_COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = TODO_COMMAND_WORD + ": Adds a todo task to the TaskList. "
            + "Parameters: DESCRIPTION \n"
            + "Example: " + TODO_COMMAND_WORD
            + " submit CS2113 survey";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Convenience constructor for todos.
     */
    public AddCommand(String description) {
        this.toAdd = new Todo(description);
    }

    /**
     * Convenience constructor for deadlines.
     */
    public AddCommand(String description, String by) {
        this.toAdd = new Deadline(description, by);
    }

    /**
     * Convenience constructor for events.
     */
    public AddCommand(String description, String from, String to) {
        this.toAdd = new Event(description, from, to);
    }

    public Task getTask() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
