package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
