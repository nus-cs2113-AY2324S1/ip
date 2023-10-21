package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

public class UnmarkTask extends Command {
    private final int unmarkTaskIndex;
    private final TaskList taskList;

    public UnmarkTask(Integer unmarkTaskIndex, TaskList taskList) {
        this.unmarkTaskIndex = unmarkTaskIndex;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.getTask(unmarkTaskIndex).markAsUndone();
        Ui.printMultipleText(new String[] {"OK, I've marked this task as not done yet:", taskList.getTask(unmarkTaskIndex).toString()});
    }
}
