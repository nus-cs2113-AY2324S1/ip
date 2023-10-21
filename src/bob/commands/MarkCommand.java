package bob.commands;

import bob.tasklist.TaskList;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private final int markIdx;

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
