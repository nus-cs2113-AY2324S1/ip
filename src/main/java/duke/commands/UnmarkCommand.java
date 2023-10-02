package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.ui.Ui;

import static duke.ui.MessageConstants.MESSAGE_UNMARK;
import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a new UnmarkCommand object with the given task index.
     *
     * @param index The index of the task to unmark as done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by unmarking the task at the given index as done.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If the task index is invalid.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
        tasks.get(index).setDone(false);
        Ui.showMessage(MESSAGE_UNMARK + tasks.get(index).toString());
    }
}