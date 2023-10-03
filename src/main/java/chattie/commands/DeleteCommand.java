package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;
import chattie.tasks.Task;

public class DeleteCommand extends Command {
    private static final int TASK_INDEX = 1;
    private static String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        String[] commandArray = command.split(" ");
        int taskNum = Integer.parseInt(commandArray[TASK_INDEX]) - 1;
        Task task = tasks.getTask(taskNum);
        tasks.delete(taskNum);
        ui.printDeleteMessage(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
