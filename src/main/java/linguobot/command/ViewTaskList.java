package linguobot.command;

import linguobot.task.TaskList;

public class ViewTaskList extends Command{
    private final TaskList taskList;

    public ViewTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    @Override
    public void execute() {
        taskList.printTaskList();
    }
}
