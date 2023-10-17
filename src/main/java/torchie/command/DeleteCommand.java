package torchie.command;

import torchie.task.TaskList;

public class DeleteCommand extends Command {
    private String status;
    private TaskList taskList;
    private int index;
    public DeleteCommand(TaskList tl, int i) {
        this.taskList = tl;
        this.index = i;
    }

    @Override
    public void handleCommand() {
        taskList.deleteTask(index);
        taskList.announceListSize();
    }
}
