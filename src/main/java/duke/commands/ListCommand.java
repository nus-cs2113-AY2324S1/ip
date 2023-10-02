package duke.commands;

import duke.tasks.Tasklist;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_LIST;

public class ListCommand extends Command {
    
    @Override
    public void execute(Tasklist tasks) {
        Ui.showMessage(MESSAGE_LIST + "\n" + tasks.tasksToString());
    }
    
}
