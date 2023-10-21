package bob.commands;

import bob.tasklist.TaskList;

/**
 * Deletes item in task list at specified index.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final int deleteIdx;

    /**
     * Creates a DeleteCommand to delete item in list at specified index.
     *
     * @param deleteIdx Index of item to delete
     */
    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    public DeleteCommand(String line) {
        this.deleteIdx = Integer.parseInt(line) - 1;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleDeleteTask(deleteIdx);
    }
}
