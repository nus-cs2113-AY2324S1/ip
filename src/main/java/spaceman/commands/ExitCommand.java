package commands;

import data.TaskList;
import data.task.Task;
import data.task.Todo;
import ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks) {
        Ui.printGoodbyeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
