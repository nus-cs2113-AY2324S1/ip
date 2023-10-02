package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

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
