package spaceman.commands;

import spaceman.data.TaskList;

/**
 * Marks a specific task in the list as done.
 */
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
