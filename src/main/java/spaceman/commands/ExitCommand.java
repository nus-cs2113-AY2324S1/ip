package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks) {
        Ui.printGoodbyeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
