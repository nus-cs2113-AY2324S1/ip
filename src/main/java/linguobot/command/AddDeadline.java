package linguobot.command;

import linguobot.Ui;
import linguobot.task.Deadline;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The AddDeadline class represents a command to add a <code>Deadline</code> task to the LinguoBot application.
 */
public class AddDeadline extends Command{
    private final Deadline taskToAdd;
    private final TaskList taskList;

    /**
     * Constructs a new AddDeadline command.
     * @param taskToAdd The <code>Deadline</code> task to be added.
     * @param taskList The taskList where the task will be added.
     */
    public AddDeadline(Deadline taskToAdd, TaskList taskList) {
        this.taskToAdd = taskToAdd;
        this.taskList = taskList;
    }

    /**
     * Executes the AddDeadline command, adding the specified <code>Deadline</code> task to the taskList and saving the updated list to taskFile.
     */
    @Override
    public void execute() {
        taskList.addTask(taskToAdd);
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Got it. I've added this task:", taskToAdd.toString(),
                "Now you have " + taskList.getNumberOfTasks() + " task(s) in the list."});
    }
}
