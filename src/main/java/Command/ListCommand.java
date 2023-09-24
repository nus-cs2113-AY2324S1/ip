package Command;

import Soccat.Task;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        if (tasks.getTaskListLength() == 0) {
            ui.displayTaskCount(tasks);
        }
        ui.displayAllTasks(tasks);
        return false;
    }
}
