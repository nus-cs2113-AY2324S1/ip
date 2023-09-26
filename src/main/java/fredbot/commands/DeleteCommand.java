package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * This function deletes a task from current tasks and print a message to output and update file
     * @param tasks an object of the TaskList class to store current tasks
     * @param storage an object of the Storage class to interact with the file on disk
     * @param ui an object of the Ui class to interact with stdout and stdin
     * @throws IOException error when there is an issue with file I/O
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        ui.printRemoveTask(tasks.removeTask(index));
        storage.addTaskstoFile(tasks);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
