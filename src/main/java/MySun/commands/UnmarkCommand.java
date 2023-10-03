package MySun.commands;

import MySun.data.TaskList;

/**
 * unMarks a task in the list.
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
