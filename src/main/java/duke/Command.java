package duke;

import java.util.ArrayList;

/**
 * The Command interface represents an action that can be executed by Duke.
 * Implementing classes should define the behavior of the execute method.
 */
public interface Command {

    /**
     * Executes the command with the given parameters.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException If an error occurs during command execution.
     */
    void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if this command indicates program exit.
     *
     * @return True if the command indicates program exit, false otherwise.
     */
    boolean isExit();
}
