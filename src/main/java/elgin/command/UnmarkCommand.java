package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int totalTasks = tasks.getTaskSize();
        if (index < 1 || index > totalTasks) {
            throw new DukeException("Please enter a valid task number.");
        }
        ui.formatPrint(tasks.setTaskIsDone(index, false));
    }
}
