package linguobot.command;

import linguobot.Ui;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;
/**
 * The DeleteTask class represents a command to delete a task from the taskList.
 */
public class DeleteTask extends Command{
    private final int deleteTaskIndex;
    private final TaskList taskList;

    /**
     * Constructs a new DeleteTask command.
     * @param deleteTaskIndex The index of the task to be deleted.
     * @param taskList The taskList storing the tasks.
     */
    public DeleteTask(Integer deleteTaskIndex, TaskList taskList) {
        this.deleteTaskIndex = deleteTaskIndex;
        this.taskList = taskList;
    }

    /**
     * Executes the DeleteTask command, removing the specified task from the taskList and saving the updated list to taskFile.
     */
    @Override
    public void execute() {
        Ui.printMultipleText(new String[] {"Noted. I've removed this task:",
                taskList.getTask(deleteTaskIndex).toString(),
        "Now you have " + (taskList.getNumberOfTasks() - 1) + " task(s) in the list."});
        taskList.deleteTask(deleteTaskIndex);
        saveTaskListToFile(taskList);
    }
}
