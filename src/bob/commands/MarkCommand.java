package bob.commands;

import bob.BobException;
import bob.tasklist.TaskList;

/**
 * Marks task in task list as complete.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private final int markIdx;

    /**
     * Creates MarkCommand to mark task at {@code markIdx} as complete.
     *
     * @param markIdx Index of task to mark as complete.
     */
    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    @Override
    public String execute(TaskList taskList) throws BobException {
        return taskList.markItem(markIdx);
    }
}
