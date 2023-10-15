package duke.commands;

import duke.tasks.Tasklist;
import duke.ui.Ui;

import static duke.ui.MessageConstants.MESSAGE_LIST;

/**
 * Represents a command to list all tasks in the Tasklist.
 */
public class ListCommand extends Command {
    
    /**
     * Executes the ListCommand by displaying all tasks in the Tasklist.
     *
     * @param tasks The Tasklist object to display.
     */
    @Override
    public void execute(Tasklist tasks) {
        Ui.showMessage(MESSAGE_LIST + "\n" + tasks.tasksToString());
    }
    
}