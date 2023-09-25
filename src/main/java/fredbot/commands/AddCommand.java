package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.task.Task;

public class AddCommand extends Command {
    private Task task = null;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (task == null) {
            System.out.println("error");
            return;
        }
        tasks.addTask(task);
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
