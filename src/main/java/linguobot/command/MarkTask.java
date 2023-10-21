package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

public class MarkTask extends Command {
    private final int markTaskIndex;
    private final TaskList taskList;

    public MarkTask(Integer markTaskIndex, TaskList taskList) {
        this.markTaskIndex = markTaskIndex;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.getTask(markTaskIndex).markAsDone();
        Ui.printMultipleText(new String[] {"Nice! I've marked this task as done:", taskList.getTask(markTaskIndex).toString()});
    }
}
