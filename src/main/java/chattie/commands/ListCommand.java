package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;
import chattie.tasks.Task;

import java.util.ArrayList;

/**
 * Deals with list command
 */
public class ListCommand extends Command {

    /**
     * Prints out the list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ArrayList<Task> list = tasks.getList();

        ui.printTaskList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
