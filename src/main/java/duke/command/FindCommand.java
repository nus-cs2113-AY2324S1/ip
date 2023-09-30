package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterList(keyword);
        ui.printFilteredTasks(filteredTasks);
    }
}
