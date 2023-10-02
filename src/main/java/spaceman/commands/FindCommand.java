package spaceman.commands;

import spaceman.data.TaskList;

/**
 * Finds and lists all the tasks that contains the keyword.
 * Keyword is non-case sensitive.
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
