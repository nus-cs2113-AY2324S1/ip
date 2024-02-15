package mudmud.command;

import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    /**
     * Creates a list command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ui.printTasks(tasks);
    }
}
