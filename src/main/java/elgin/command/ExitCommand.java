package elgin.command;

import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;


public class ExitCommand extends Command {

    /**
     * Executes to change isContinue to False to indicate user
     * exiting the program.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui for standardised output of program.
     * @param storage Storage that stores and loads tasks from disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isContinue = false;
    }
}
