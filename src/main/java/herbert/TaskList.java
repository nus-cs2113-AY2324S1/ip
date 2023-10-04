package herbert;

import task.Task;

import java.util.ArrayList;

/**
 * This class handles the in-program storage, retrieval, deletion, and addition of Herbert tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a given task to the TaskList.
     * @param t The new task to be added to the TaskList.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the task at the specified task index from the TaskList.
     * @param index The (0-indexed) index of the task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task at a given index from the TaskList.
     * @param index The (0-indexed) index of the task to be retrieved.
     * @return The Task object retrieved from the TaskList.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Retrieves the number of Tasks stored in the TaskList.
     * @return The number of tasks stored in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

}
