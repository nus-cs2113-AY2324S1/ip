package mudmud.command;

import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

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
