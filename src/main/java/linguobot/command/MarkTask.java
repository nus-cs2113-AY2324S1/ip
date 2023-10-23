package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The MarkTask class represents a command to mark a task in the taskList.
 */
public class MarkTask extends Command {
    private final int markTaskIndex;
    private final TaskList taskList;

    /**
     * Constructs a MarkTask command with the given task index and taskList.
     * @param markTaskIndex The index of the task to mark.
     * @param taskList TaskList storing tasks.
     */
    public MarkTask(Integer markTaskIndex, TaskList taskList) {
        this.markTaskIndex = markTaskIndex;
        this.taskList = taskList;
    }

    /**
     * Executes the MarkTask command by marking the specified task as done in the TaskList,
     * and updating the taskList to taskFile.
     */
    @Override
    public void execute() {
        taskList.getTask(markTaskIndex).markAsDone();
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Nice! I've marked this task as done:",
                taskList.getTask(markTaskIndex).toString()});
    }
}
