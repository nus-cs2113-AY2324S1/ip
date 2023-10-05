package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

public class InvalidCommand extends Command {
    public InvalidCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayError("Uh-oh! Invalid command. Please try again!\n");
        ui.displayHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
