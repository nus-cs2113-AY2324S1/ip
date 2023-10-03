package MySun.commands;

import MySun.data.TaskList;

/**
 * Finds and lists all the tasks that contains the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.findTask(keyword);
    }
}

