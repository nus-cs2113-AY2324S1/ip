package MySun.commands;

import MySun.data.TaskList;

/**
 * Deletes a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskIndex);
    }
}
