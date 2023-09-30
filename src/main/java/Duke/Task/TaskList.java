package Duke.Task;

import java.util.ArrayList;

/**
 * A data structure that is created to contain many tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the TaskList
     *
     * @return size of TaskList
     */
    public int getNumTask() {
        return tasks.size();
    }

    /**
     * Add a task to the TaskList
     *
     * @param task Task object to be added to the TaskList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task details with a given index.
     *
     * @param taskIndex Integer value of the index of the task
     * @return Task object
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    /**
     * Removes a task based on the taskIndex.
     *
     * @param taskIndex taskIndex of a task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }
}
