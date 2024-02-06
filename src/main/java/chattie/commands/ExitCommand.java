package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

/**
 * Deals with exit command
 */
public class ExitCommand extends Command {

    /**
     * Prints bye message
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
