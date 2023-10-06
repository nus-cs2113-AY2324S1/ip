package lemon.commands;

import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;

import java.util.ArrayList;

/**
 * Represents a command to list tasks in the task list.
 * This command displays the data of the tasks to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command to display task list.
     * Displays the task data through the user interface.
     * If the list is empty, it displays a message to indicate that it is empty.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.displayEmptyList();
        } else {
            ArrayList<Task> taskList = tasks.getTasks();
            ui.displayTaskList(taskList);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
