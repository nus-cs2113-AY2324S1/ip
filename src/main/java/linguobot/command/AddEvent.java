package linguobot.command;

import linguobot.Ui;
import linguobot.task.Event;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The AddEvent class represents a command to add an <code>Event</code> task to the LinguoBot application.
 */
public class AddEvent extends Command{
    private final Event taskToAdd;
    private final TaskList taskList;

    /**
     * Constructs a new AddEvent command.
     * @param taskToAdd The <code>Event</code> task to be added.
     * @param taskList The taskList where the task will be added.
     */
    public AddEvent(Event taskToAdd, TaskList taskList) {
        this.taskToAdd = taskToAdd;
        this.taskList = taskList;
    }

    /**
     * Executes the AddEvent command, adding the specified <code>Event</code> task to the taskList and saving the updated list to taskFile.
     */
    @Override
    public void execute() {
        taskList.addTask(taskToAdd);
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Got it. I've added this task:", taskToAdd.toString(),
                "Now you have " + taskList.getNumberOfTasks() + " task(s) in the list."});
    }
}
