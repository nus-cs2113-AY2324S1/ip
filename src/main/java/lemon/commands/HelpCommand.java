package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
