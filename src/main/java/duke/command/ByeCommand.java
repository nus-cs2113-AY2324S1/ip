package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ui.tellGoodbye();
    }

}
