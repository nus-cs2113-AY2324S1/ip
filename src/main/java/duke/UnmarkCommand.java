package duke;

import java.util.ArrayList;

/**
 * The UnmarkCommand class represents a command to unmark a task as done.
 * It implements the Command interface.
 */
public class UnmarkCommand implements Command {
    private final String input;

    /**
     * Constructs an UnmarkCommand with the specified input.
     *
     * @param input The input string that specifies the task to be unmarked.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the UnmarkCommand to unmark a task as done.
     *
     * @param tasks   The list of tasks to be operated on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file.
     * @throws DukeException If there is an error during the unmarking process.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        TaskList.unmarkTask(input, tasks);
    }

    /**
     * Indicates whether this command represents a program exit.
     *
     * @return False, as this command does not indicate program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
