package Commands;

import Cara.*;
import Tasks.Task;
import Tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final String input;

    /**
     * Constructs a DeleteCommand with the given input.
     *
     * @param input The input string representing the task to be deleted.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the DeleteCommand by deleting the task from the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws CaraException If an error occurs during task deletion.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws CaraException {
        TaskList.deleteTask(input, tasks);
    }

    /**
     * Indicates whether this command triggers program exit.
     *
     * @return False because this command does not indicate program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
