package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;


public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of the task to perform action on.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes to delete the task from TaskList.
     * Prints success message when task is deleted.
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
        ui.formatPrint(tasks.deleteTask(index));
    }
}
