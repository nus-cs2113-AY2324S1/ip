package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.data.task.Task;

/**
 * Adds a task into the list of tasks.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(task);
    }
}
