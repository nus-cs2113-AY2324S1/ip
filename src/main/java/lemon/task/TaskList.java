package lemon.task;

import java.util.ArrayList;

/**
 * Utility class for managing tasks in the task list.
 * This class adds, deletes, mark and unmark tasks.
 * It retrieves task list, gets the size of the task list and checks if the task list is empty.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds specified task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on the specified task index.
     *
     * @param taskIndex Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Marks a task in the task list as done based on the specified task index.
     *
     * @param taskIndex Index of the task to be marked as done.
     * @return Task marked as done.
     */
    public Task markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        return tasks.get(taskIndex);
    }

    /**
     * Marks a task in the task list as not done based on the specified task index.
     *
     * @param taskIndex Index of the task to be marked as not done.
     * @return Task marked as not done.
     */
    public Task unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markAsNotDone();
        return tasks.get(taskIndex);
    }

<<<<<<< HEAD
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

=======
    /**
     * Returns a task list with the task data.
     *
     * @return Task list.
     */
>>>>>>> branch-A-JavaDoc
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns whether there are tasks in the task list.
     *
     * @return True if the task is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
