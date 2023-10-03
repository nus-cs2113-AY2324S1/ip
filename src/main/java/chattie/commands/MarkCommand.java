package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

public class MarkCommand extends Command {

    private static final int TASK_INDEX = 1;
    private static String command;

    public MarkCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        String[] commandArray = command.split(" ");
        int taskNum = Integer.parseInt(commandArray[TASK_INDEX]) - 1;

        tasks.mark(taskNum);
        ui.printMarkMessage(tasks, taskNum);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
