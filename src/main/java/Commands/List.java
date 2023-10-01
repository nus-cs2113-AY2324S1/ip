package Commands;

import Data.TaskList;
import Ui.Ui;

/**
 * Command to list all tasks
 */
public class List extends Command{

    /**
     * Executes the command to list all tasks
     * @param list Tasklist to be read
     * @param ui Ui to print messages
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        list.getTasks(ui);
    }
}
