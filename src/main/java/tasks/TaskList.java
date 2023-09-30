package tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     * 
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTaskIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Marks a specified task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone(true);
    }

    /**
     * Marks a specified task as undone.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        Task task = tasks.get(index);
        task.markAsDone(false);
    }

    /**
     * Prints the list of tasks.
     */
    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("ERROR: ☹ OOPS!!! No tasks is available.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     * 
     * @param keyword The keyword to be searched for.
     * @return The list of tasks that contain the specified keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
