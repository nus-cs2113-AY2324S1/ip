package duke;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand implements Command {
    private final String input;

    /**
     * Constructs a MarkCommand with the given input.
     *
     * @param input The input string representing the task to be marked as completed.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the MarkCommand by marking the specified task as completed.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager (not used in this command).
     * @throws DukeException If an error occurs during task marking.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        TaskList.markTask(input, tasks);
    }

    /**
     * Checks if this command indicates program exit.
     *
     * @return False because this command does not indicate program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
