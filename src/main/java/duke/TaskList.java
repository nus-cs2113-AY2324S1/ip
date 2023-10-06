package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks managed by Duke.
 * It allows adding, listing, marking, and deleting tasks, as well as finding tasks by keyword.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    //Creates a new empty task list
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Lists all tasks in the task list.
     * Displays a message if the list is empty.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Marks a task as done based on its index in the task list.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).markAsDone();
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Marks a task as undone based on its index in the task list.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks matching the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Deletes a task based on its index in the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n" + removedTask);
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
        return null;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a task from the task list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Gets the count of tasks in the task list.
     *
     * @return The count of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
