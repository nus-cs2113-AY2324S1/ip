package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
