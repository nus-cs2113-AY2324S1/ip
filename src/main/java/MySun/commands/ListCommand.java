package MySun.commands;

import MySun.ui.Ui;
import MySun.data.TaskList;

/**
 * Lists all the tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.showTaskList(tasks);
    }
}
