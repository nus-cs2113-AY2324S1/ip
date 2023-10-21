package bob.commands;

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

    public MarkCommand(String line) {
        this.markIdx = Integer.parseInt(line) - 1;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.markItem(markIdx);
    }
}
