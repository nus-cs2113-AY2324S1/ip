package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Generates TaskList object.
     * @param tasks list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     * @return list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes task from the list at specified index.
     * @param index Index of task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }
}
