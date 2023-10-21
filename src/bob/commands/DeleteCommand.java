package bob.commands;

import bob.tasklist.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final int deleteIdx;

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
