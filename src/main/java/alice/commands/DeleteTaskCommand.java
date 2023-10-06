package alice.commands;

import alice.tasks.TaskList;

public class DeleteTaskCommand extends Command{
    private int deleteIndex;
    private TaskList tasks;
    public DeleteTaskCommand(int deleteIndex, TaskList tasks){
        this.deleteIndex = deleteIndex;
        this.tasks = tasks;
    }

    public void handleCommand() {
        tasks.deleteTask(deleteIndex);
    }
}
