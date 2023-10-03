package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;


public class MarkCommand extends Command {

    private int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index Index of the task to perform action on.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes to mark the task as completed.
     * Prints success message when task is marked as completed.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui for standardised output of program.
     * @param storage Storage that stores and loads tasks from disk.
     * @throws DukeException If invalid task index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int totalTasks = tasks.getTaskSize();

        if (index < 1 || index > totalTasks) {
            throw new DukeException("Please enter a valid task number.");
        }
        ui.formatPrint(tasks.setTaskIsDone(index, true));
    }
}
