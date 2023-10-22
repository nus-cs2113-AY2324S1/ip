package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The UnmarkTask class represents a command to unmark a task in the taskList.
 */
public class UnmarkTask extends Command {
    private final int unmarkTaskIndex;
    private final TaskList taskList;

    /**
     * Constructs an UnmarkTask command with the given task index and taskList.
     * @param unmarkTaskIndex The index of the task to unmark.
     * @param taskList TaskList storing tasks.
     */
    public UnmarkTask(Integer unmarkTaskIndex, TaskList taskList) {
        this.unmarkTaskIndex = unmarkTaskIndex;
        this.taskList = taskList;
    }

    /**
     * Executes the UnmarkTask command by marking the specified task as undone in the TaskList,
     * and updating the taskList to taskFile.
     */
    @Override
    public void execute() {
        taskList.getTask(unmarkTaskIndex).markAsUndone();
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"OK, I've marked this task as not done yet:",
                taskList.getTask(unmarkTaskIndex).toString()});
    }
}
