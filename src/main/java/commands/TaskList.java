package commands;

import java.util.ArrayList;

/**
 * The `TaskList` class represents a list of tasks managed by the Duke application.
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasks = tasksList;
    }

    /**
     * Retrieves the task based on its index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task based on its index
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Adds a task to the task manager.
     *
     * <p>This method adds the provided {@code Task} object to the list of tasks managed by
     * the task manager.
     * </p>
     *
     * @param task The {@code Task} object to be added to the task manager.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a list of all tasks.
     *
     * <p>This method returns an {@code ArrayList} containing all the tasks currently stored
     * in the task manager. If there are no tasks, an empty {@code ArrayList} is returned.
     * </p>
     *
     * @return An {@code ArrayList} of {@code Task} objects representing all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks
     */
    public int size() {
        return tasks.size();
    }


    /**
     * Prints a list of tasks with their status icons and descriptions.
     */

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            System.out.println(
                    count + "." + "[" + task.getType() + "]" + "[" + task.getStatusIcon() + "]" + task);
            count += 1;
        }
    }

}
