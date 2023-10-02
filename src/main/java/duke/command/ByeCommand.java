package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * Represents a bye command.
 */
public class ByeCommand extends Command {

    /**
     * Creates a bye command.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ui.tellGoodbye();
    }
}
