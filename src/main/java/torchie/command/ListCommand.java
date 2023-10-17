package torchie.command;

import torchie.task.TaskList;

public class ListCommand extends Command{
    private TaskList taskList;

    public ListCommand(TaskList tl) {
        this.taskList = tl;
    }

    @Override
    public void handleCommand() {
        taskList.showTasks();
    }


}
