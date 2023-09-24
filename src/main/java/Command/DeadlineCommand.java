package Command;

import Soccat.Deadline;
import Storage.TaskList;
import Storage.Storage;
import Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a deadline.
 * A <code>DeadlineCommand</code> corresponds to a command
 * consisting of a deadline with a <code>task</code> and <code>deadline</code>.
 * */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String INVALID_PROMPT = "Oops, please try deadline <task> /by <deadline>!";

    private final Deadline deadlineTask;

    public DeadlineCommand(String taskName, String deadline) {
        this.deadlineTask = new Deadline(taskName, deadline);
    }

    /**
     * Executes the deadline command to add a deadline task
     * to the list of tasks, and update the storage file.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        try {
            tasks.addTask(deadlineTask, taskFile);
            ui.displayAddedTask(deadlineTask, tasks, COMMAND_WORD);
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
        return false;
    }
}