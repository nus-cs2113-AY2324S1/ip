package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

/**
 * Represents a command to mark tasks in the task list.
 * This command marks the specified task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int taskIndex;
    private final Parser parser;

    /**
     * Constructs a MarkCommand with the specified task index and a Parser object.
     *
     * @param taskIndex Index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.parser = new Parser();
    }

    /**
     * Executes the command to mark a task in the list as done.
     * Checks whether the task index is in the range of the list,
     * marks the task as done, displays the task that is marked,
     * and saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     * @throws LemonException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
        parser.checkTaskNumberRange(taskIndex, tasks);
        Task markedTask = tasks.markTask(taskIndex);
        ui.displayMarkedTask(markedTask);
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
