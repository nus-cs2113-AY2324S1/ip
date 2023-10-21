package bob.commands;

import bob.BobException;
import bob.tasklist.TaskList;

/**
 * Marks a task in task list as incomplete.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int markIdx;

    /**
     * Creates UnmarkCommand to mark task at {@code markIdx} as incomplete.
     *
     * @param markIdx Index of task to mark as incomplete.
     */
    public UnmarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    public UnmarkCommand(String line) {
        this.markIdx = Integer.parseInt(line) - 1;
    }

    @Override
    public String execute(TaskList taskList) throws BobException {
        return taskList.unmarkItem(markIdx);
    }

}
