package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

/**
 * Deals with mark command
 */
public class MarkCommand extends Command {

    private static final int TASK_INDEX = 1;
    private static String command;

    public MarkCommand(String command) {
        this.command = command;
    }

    /**
     * Marks the specified task as done
     *
     * @param tasks List of tasks
     * @param ui User interface
     */
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
