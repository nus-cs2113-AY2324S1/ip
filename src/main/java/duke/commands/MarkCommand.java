package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.ui.Ui;

import static duke.ui.MessageConstants.MESSAGE_MARK;
import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    
    private int index;
    
    /**
     * Constructs a new MarkCommand object with the given task index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the MarkCommand by marking the task at the given index as done.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If the task index is invalid.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
        tasks.get(index).setDone(true);
        Ui.showMessage(MESSAGE_MARK + tasks.get(index).toString());
    }
}