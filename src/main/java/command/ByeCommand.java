package command;
import exception.FrankException;
import task.TaskList;
import utility.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    /**
     * Shuts down the system. Does not save beforehand
     * @param tasks TaskList of all the current tasks
     * @param ui the current user interface
     * @throws FrankException Unique Exception
     * @throws IOException Input Exception
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        ui.showGoodbye();
        System.exit(0);
    }
}
