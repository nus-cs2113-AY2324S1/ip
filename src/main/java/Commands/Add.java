package Commands;

import Tasks.Task;
import Tasks.TaskList;
import UI.Ui;

/**
 * Represents a command to add tasks to a TaskList in the Barbie-themed task manager.
 */
public class Add extends Command {
    private final Task task;

    /**
     * Constructor for "Add" command with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the "Add" command by adding the task to the TaskList and printing messages to the user.
     *
     * @param list The task list on which the command should operate.
     */
    @Override
    public void run(TaskList list) {
        list.addTask(task);
        Ui.printTexts(new String[] {
                "Barbie-approved! You've added this glamorous task:",
                task.toString(),
                "Now your list is sparkling with " + list.getSize() + " glamorous tasks, darling!"
        });
    }
}
