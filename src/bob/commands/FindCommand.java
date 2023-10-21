package bob.commands;

import bob.tasklist.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String line) {
        keyword = line;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleFindTask(keyword);
    }

}
