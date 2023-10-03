package MySun.commands;

import MySun.data.TaskList;

/**
 * Marks a specific task in the list as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.unMarkTask(taskIndex);
    }
}
