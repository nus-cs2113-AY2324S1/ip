package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

import java.io.IOException;

/**
 * Represent a class for executing mark task command
 */
public class MarkCommand extends Command{
    private int index;
    private boolean mark = false;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * This function marks a task in current tasklist and print a message to output and update file
     * @param tasks an object of the TaskList class to store current tasks
     * @param storage an object of the Storage class to interact with the file on disk
     * @param ui an object of the Ui class to interact with stdout and stdin
     * @throws IOException error when there is an issue with file I/O
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        tasks.markTask(index, mark);
        ui.printMarkTask(tasks.getTask(index));
        storage.addTaskstoFile(tasks);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
