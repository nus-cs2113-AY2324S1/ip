package duke;
import duke.Ui;

import duke.Command;
import duke.Storage;
import duke.TaskList;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
