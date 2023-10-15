package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.ui.Ui;

import static duke.ui.MessageConstants.MESSAGE_DELETE;
import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;

/**
 * Represents a command to delete a task from the Tasklist.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new DeleteCommand object with the given task index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the task at the given index from the Tasklist.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If the task index is invalid.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
        Ui.showMessage(MESSAGE_DELETE + tasks.get(index).toString());
        tasks.remove(index);
    }
    
}