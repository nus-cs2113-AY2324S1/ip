package luke.tasks;

import java.util.ArrayList;
import luke.user.LukeException;

/**
 * The TaskList Class contains the task list and provides methods to manage and retrieve tasks from the task list.
 */
public class TaskList{

    private ArrayList<Task> mainTaskList;
    public int numberOfTasks;

    /**
     * Constructs a TaskList object from an existing ArrayList of tasks.
     *
     * @param tasklist An ArrayList of Task objects to initialize the task list with.
     * @throws LukeException If an error specific to the LukeTime application occurs during initialization.
     */

    public TaskList(ArrayList<Task> tasklist) throws LukeException, NullPointerException {
        mainTaskList = new ArrayList<Task>();
        numberOfTasks = 0;
        for (Task item: tasklist) {
            addTask(item);
        }
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        mainTaskList = new ArrayList<Task>();
        numberOfTasks = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskName The Task object to be added.
     */
    public void addTask(Task taskName) {
        mainTaskList.add(taskName);
        numberOfTasks += 1;
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param taskNumber The index of the task to be removed.
     */
    public void removeTask(int taskNumber) {
        mainTaskList.remove(taskNumber);
        numberOfTasks -= 1;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return numberOfTasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (numberOfTasks <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param taskNumber The index of the task to be retrieved.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task get(int taskNumber) throws IndexOutOfBoundsException {
        return mainTaskList.get(taskNumber);
    }

    /**
     * Returns the main ArrayList of Task objects containing the task list.
     *
     * @return An ArrayList of Task objects representing the task list.
     */
    public ArrayList<Task> getMainTaskList() {
        return mainTaskList;
    }
}
