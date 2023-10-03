package MySun.commands;

import MySun.data.TaskList;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.markTask(taskIndex);
    }
}
