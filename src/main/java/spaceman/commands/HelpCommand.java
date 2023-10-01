package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.showHelpMenu();
    }
}
