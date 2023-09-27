package commands;

import data.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskIndex);
    }
}
