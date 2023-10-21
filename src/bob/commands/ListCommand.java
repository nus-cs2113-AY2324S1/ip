package bob.commands;

import bob.tasklist.TaskList;

/**
 * Lists items in task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleGetList();
    }
}
