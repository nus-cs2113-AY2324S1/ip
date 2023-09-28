package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ui.printTasks(tasks);
    }
}
