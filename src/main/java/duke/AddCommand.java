package duke;

import java.util.ArrayList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {
    private final String input;

    /**
     * Constructs an AddCommand with the given input.
     *
     * @param input The input string representing the task to be added.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the AddCommand by adding the task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException If an error occurs during task addition.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        TaskList.addTaskToList(input, tasks);
    }

    /**
     * Indicates whether this command triggers program exit.
     *
     * @return False because this command does not indicate program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
