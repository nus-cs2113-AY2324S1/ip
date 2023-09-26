package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;
import fredbot.task.Task;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task = null;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (task == null) {
            System.out.println("error");
            return;
        }
        tasks.addTask(task);
        ui.printAddTask(tasks);
        try {
            storage.addTaskstoFile(tasks);
        } catch (IOException e) {
            System.out.println("unable to add to file");
        }
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
