package alice.commands;

import alice.tasks.TaskList;

public class ListTasksCommand extends Command{
    TaskList tasks;
    public ListTasksCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void handleCommand() {
        tasks.listTasks();
    }
}
