package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class ListCommand extends Command {
    /**
     * Lists all tasks
     * @param ui Used for printing
     * @param tasks Current Task List
     */
    @Override
    public void runCommand(Ui ui, TaskList tasks) {
        ui.listHandler(tasks.returnTaskList());
    }
}
