package Commands;

import Data.Task;
import Data.TaskList;

import Ui.Ui;

/**
 * Command to add a task to the list
 */
public class Add extends Command{
    private final Task task;

    /**
     * Constructor for Add command
     * @param task Task to be added
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the list
     * @param list Tasklist to be modified
     * @param ui Ui to print messages
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        list.add(task);
        String[] text = {"Added: " + task.getDescription(), "Now you have " + list.size() + " tasks in the list."};
        ui.printMultipleText(text);
    }
}
