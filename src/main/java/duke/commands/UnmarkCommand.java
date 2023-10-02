package duke.commands;

import duke.tasks.Tasklist;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_UNMARK;
import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;

import duke.exception.DukeException;

public class UnmarkCommand extends Command {
    
        private int index;
    
        public UnmarkCommand(int index) {
            this.index = index;
        }
    
        @Override
        public void execute(Tasklist tasks) throws DukeException {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
            }
            tasks.get(index).setDone(false);
            Ui.showMessage(MESSAGE_UNMARK + tasks.get(index).toString());
        }
    
}
