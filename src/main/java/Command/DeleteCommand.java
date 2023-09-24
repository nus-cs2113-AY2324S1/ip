package Command;

import Soccat.Task;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 * A <code>DeleteCommand</code> corresponds to a command
 * pointing to an integer <code>taskIndex</code> to be deleted.
 * */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    public static final String INVALID_PROMPT = "Oops, please try delete <taskIndex>!";
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command to remove a task from
     * the list of tasks as well as the task file.
     *
     * @param tasks The taskList object containing tasks from which the task is removed
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file from which the task is removed
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        int taskListLength = tasks.getTaskListLength();
        if (taskListLength == 0) {
            ui.displayTaskCount(tasks);
            return false;
        }
        try {
            Task task = tasks.removeTask(taskIndex, taskFile);
            ui.displayRemovedTask(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.displayError(ui.INDEX_OFB_EXCEPTION_MESSAGE + tasks.getTaskListLength());
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
        return false;
    }
}
