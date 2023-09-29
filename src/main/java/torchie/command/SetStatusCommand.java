package torchie.command;

import torchie.task.TaskList;

public class SetStatusCommand extends Command{
    private String status;
    private TaskList taskList;
    private int index;
    public SetStatusCommand(String s, TaskList tl, int i) {
        this.status = s;
        this.taskList = tl;
        this.index = i;
    }

    public void handleCommand() {
        if (this.status.equals("mark")) {
            taskList.markTask(index);
        } else if (this.status.equals("unmark")) {
            taskList.unmarkTask(index);
        }
    }
}
