package commands;

import data.TaskList;
import data.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks) {
        tasks.addTask(task);
    }
}
