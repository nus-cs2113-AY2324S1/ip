package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

public class DeleteTask extends Command{
    private final int deleteTaskIndex;
    private final TaskList taskList;

    public DeleteTask(Integer deleteTaskIndex, TaskList taskList) {
        this.deleteTaskIndex = deleteTaskIndex;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        Ui.printMultipleText(new String[] {"Noted. I've removed this task:", taskList.getTask(deleteTaskIndex).toString(),
        "Now you have " + (taskList.getNumberOfTasks() - 1) + " task(s) in the list."});
        taskList.deleteTask(deleteTaskIndex);
    }
}
