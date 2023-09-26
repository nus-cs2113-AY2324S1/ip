package RC;

import RC.UI.Ui;
import RC.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private static final String MESSAGE_INTEGER_MISSING = "\tOOPS!!! Please enter a valid integer.";
    private static final String MESSAGE_INDEX_OUT_OF_RANGE = "\tOOPS!!! Index is out of range of list.";
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to be added.
     * @param ui The user interface for displaying messages.
     */
    public void add(Task task, Ui ui) {
        tasks.add(task);

        String message = "\tGot it. I've added this task:\n\t  " + task.toString() + "\n\tNow you have " + tasks.size();
        message += getNumTasks();

        ui.showMessage(message);
    }

    /**
     * Loads a task to the task list.
     *
     * @param task The task to be loaded.
     */
    public void load(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the task list based on its index and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @param ui The user interface for displaying messages.
     * @throws RCException If the provided index is invalid.
     */
    public void delete(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        String message = "\tNoted. I've removed the following task:\n\t  " + tasks.get(taskNum);
        tasks.remove(taskNum);
        message += "\n\tNow you have " + tasks.size();
        message += getNumTasks();
        ui.showMessage(message);
    }

    /**
     * Marks a task as done based in its index and displays a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @param ui The user interface for displaying messages.
     * @throws RCException If the provided index is invalid.
     */
    public void markAsDone(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).markAsDone();
        ui.showMessage("\tNice! I've marked this task as done:\n\t  " + tasks.get(taskNum));
    }

    /**
     * Unmarks a task as done based in its index and displays a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @param ui The user interface for displaying messages.
     * @throws RCException If the provided index is invalid.
     */
    public void unmarkTask(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).unmarkTask();
        ui.showMessage("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(taskNum));
    }

    /**
     * Checks if the provided index is valid for accessing the tasks in the list.
     *
     * @param index The index to be checked.
     * @return True if the index is within the bounds specified, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0 && index < tasks.size());
    }

    private String getNumTasks() {
        if (tasks.size() > 1) {
            return " tasks in the list.";
        }
        return " task in the list.";
    }

    private int getTaskNum(String index) throws RCException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new RCException(MESSAGE_INTEGER_MISSING);
        }

        if (!isValidIndex(taskNum)) {
            throw new RCException(MESSAGE_INDEX_OUT_OF_RANGE);
        }
        return taskNum;
    }
}
