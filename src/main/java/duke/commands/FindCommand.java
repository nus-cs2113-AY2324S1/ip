package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_FIND;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(Tasklist tasks) throws DukeException {
        Ui.showMessage(MESSAGE_FIND + "\n" + tasks.findTasksToString(keyword));
    }
    
}
