package bob.commands;

import bob.tasklist.TaskList;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int markIdx;

    public UnmarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    public UnmarkCommand(String line) {
        this.markIdx = Integer.parseInt(line) - 1;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.unmarkItem(markIdx);
    }

}
