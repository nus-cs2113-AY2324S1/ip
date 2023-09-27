package spaceman.commands;

import spaceman.data.TaskList;

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
