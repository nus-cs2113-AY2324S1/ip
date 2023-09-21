package Data;

import Exceptions.CSGPTMissingTaskException;
import java.util.ArrayList;
import Ui.Ui;

/**
 * Class to store the list of tasks
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds a task to the list
     * @param task Task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the list
     * @param taskNumber Task number to be deleted
     * @throws CSGPTMissingTaskException Exception thrown when the task cannot be found
     */
    public void delete(int taskNumber) throws CSGPTMissingTaskException {
        if (taskNumber > list.size() || taskNumber < 1)
            throw new CSGPTMissingTaskException();
        list.remove(taskNumber - 1);
    }

    /**
     * Gets a task from the list
     * @param taskNumber Task number of task to be retrieved
     * @return Task to be retrieved
     */
    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    /**
     * Marks a task as done or not done
     * @param taskNumber Task number of task to be marked
     * @param isDone Boolean to indicate if the task is done or not done
     * @throws CSGPTMissingTaskException Exception thrown when the task cannot be found
     */
    public void mark(int taskNumber, boolean isDone) throws CSGPTMissingTaskException {
        if (taskNumber > list.size() || taskNumber < 1)
            throw new CSGPTMissingTaskException();
        list.get(taskNumber - 1).setDone(isDone);
    }

    /**
     * Returns the size of the list
     * @return Size of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Prints the list of tasks
     * @param ui Ui to print messages
     */
    public void getTasks(Ui ui) {
        if (list.isEmpty()) {
            ui.printText("You have no tasks at hand, mortal.");
            return;
        }
        String[] text = new String[list.size() + 1];
        text[0] = "These are the chores you have at hand, mortal:";
        for (int i = 0; i < list.size(); i++) {
            text[i+1] = ((i + 1) + ". " + list.get(i).toString());
        }
        ui.printMultipleText(text);
    }
}
