package command;
import exception.FrankException;
import task.TaskList;
import utility.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        ui.showGoodbye();
        System.exit(0);
    }
}
