package torchie.command;

import torchie.task.Task;
import torchie.task.TaskList;

public class AddCommand extends Command{
    private Task task;
    private TaskList taskList;

    public AddCommand(Task t, TaskList tl) {
        this.task = t;
        this.taskList = tl;
    }

    @Override
    public void handleCommand() {
        taskList.addTask(task);
        task.announceTaskAdd();
        taskList.announceListSize();
    }
}
