package mudmud.command;

import mudmud.storage.Storage;
import mudmud.task.Task;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

import java.util.ArrayList;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a find command.
     *
     * @param keyword The selected keyword to find.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterTasks(keyword);
        ui.printFilteredTasks(filteredTasks);
    }
}
