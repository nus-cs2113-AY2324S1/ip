package duke.commands;

import duke.tasks.Tasklist;
import duke.exception.DukeException;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_DELETE;
import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Tasklist tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
        Ui.showMessage(MESSAGE_DELETE + tasks.get(index).toString());
        tasks.remove(index);
    }
    
}
