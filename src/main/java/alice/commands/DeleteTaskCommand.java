package alice.commands;

import alice.tasks.TaskList;

public class DeleteTaskCommand extends Command{
    int deleteIndex;
    TaskList tasks;
    public DeleteTaskCommand(int deleteIndex, TaskList tasks){
        this.deleteIndex = deleteIndex;
        this.tasks = tasks;
    }

    public void handleCommand() {
        tasks.deleteTask(deleteIndex);
    }
}
