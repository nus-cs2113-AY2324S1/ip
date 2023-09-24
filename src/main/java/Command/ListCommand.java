package Command;

import Soccat.Task;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks.
 * */

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    /**
     * Display all tasks to the users.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
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
