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

    /**
     * This function adds a task to current tasks and print a message to output and update file
     * @param tasks an object of the TaskList class to store current tasks
     * @param storage an object of the Storage class to interact with the file on disk
     * @param ui an object of the Ui class to interact with stdout and stdin
     * @throws IOException error when there is an issue with file I/O
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (task == null) {
            System.out.println("error");
            return;
        }
        tasks.addTask(task);
        ui.printAddTask(tasks);
        storage.addTaskstoFile(tasks);
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
