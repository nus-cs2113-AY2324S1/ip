package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

/**
 * Represents a command to delete a task.
 * This command deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskIndex;
    private final Parser parser;

    /**
     * Constructs a DeleteCommand object with the specified task index.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.parser = new Parser();
    }

    /**
     * Executes the command to delete a task.
     * Deletes the specified task from the task list and
     * saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     * @throws LemonException If task index is out of the range of task list.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
        parser.checkTaskNumberRange(taskIndex, tasks);
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.displayDeletedTask(deletedTask, tasks);
        storage.save(tasks.getTasks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
