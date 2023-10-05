package alice.commands;

import alice.tasks.TaskList;

public class FindTaskCommand extends Command{
    TaskList tasks;
    String keyword;
    public FindTaskCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    @Override
    public void handleCommand() {
        tasks.printFilteredTasks(keyword);
    }
}
