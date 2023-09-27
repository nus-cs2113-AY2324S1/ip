package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

/**
 * Abstract class Command that can be executed on Task.
 */
public abstract class Command {

    public boolean isContinue = true;

    /**
     * Abstract method Execute that contains the logic of the command.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui for standardised output of program.
     * @param storage Storage that stores and loads tasks from disk.
     * @throws DukeException If error executing command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean getIsContinue() {
        return isContinue;
    }
}
