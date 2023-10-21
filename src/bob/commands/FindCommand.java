package bob.commands;

import bob.tasklist.TaskList;

/**
 * Finds tasks which contain specified keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Creates FindCommand to find keyword in task list.
     *
     * @param line Keyword to look for in task list.
     */
    public FindCommand(String line) {
        keyword = line;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleFindTask(keyword);
    }

}
