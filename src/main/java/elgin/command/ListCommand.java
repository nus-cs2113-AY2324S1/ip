package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;


public class ListCommand extends Command {

    /**
     * Executes and prints all the tasks formatted by the Ui.
     *
     * @param tasks TaskList of tasks
     * @param ui Ui for standardised output of program
     * @param storage Storage that stores and loads tasks from disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.formatPrint(tasks.listTasks());
    }
}
