package Command;

import Storage.Storage;
import Storage.TaskList;
import Soccat.Task;
import Ui.Ui;

import java.io.IOException;

/**
 * Represents a command mark or unmark tasks.
 * A <code>MarkCommand</code> corresponds to a command
 * consisting of a taskIndex and updates the task
 * to boolean <code>isDone</code>.
 * */

public class MarkCommand extends Command{
    public static final String COMMAND_WORD_DONE = "mark";
    public static final boolean MARK_DONE = true;
    public static final String COMMAND_WORD_UNDONE = "unmark";
    public static final boolean MARK_UNDONE = false;
    public static final String INVALID_PROMPT = "Oops, please try mark <taskIndex> or unmark <taskIndex>!";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private final boolean isDone;
    private final int taskIndex;


    public MarkCommand(boolean isDone, int taskIndex) {
        this.isDone = isDone;
        this.taskIndex = taskIndex;
    }

    /**
     * Mark a specific task in the task list to
     * either done or undone.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
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
        Task task;
        try {
            task = tasks.markTask(taskIndex, isDone, tasks, taskFile);
            ui.displayMessage((isDone) ? DONE_MESSAGE : UNDONE_MESSAGE);
            ui.displayTask(task);
        } catch (IndexOutOfBoundsException e) {
            ui.displayError(ui.INDEX_OFB_EXCEPTION_MESSAGE + tasks.getTaskListLength());
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
        return false;
    }
}
