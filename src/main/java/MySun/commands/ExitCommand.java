package MySun.commands;

import MySun.ui.Ui;
import MySun.data.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.showGoodbyeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
