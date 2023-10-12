package python.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the functionality of a collection of tasks
 */
public class TaskList {
    final static private List<Task> tasks = new ArrayList<>();

    /**
     * Returns a task on the given index
     *
     * @param taskNo Index of the task
     * @return Task at the index
     */
    public static Task getTask(int taskNo) {
        return tasks.get(taskNo);
    }

    /**
     * Returns all the tasks as a <code>List</code>
     *
     * @return Returns all the tasks as a <code>List</code>
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks
     *
     * @return Returns the number of tasks
     */
    public static int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Marks a task
     *
     * @param taskNo The index of the task
     */
    public static void markTask(int taskNo) {
        tasks.get(taskNo).setDone(true);
    }

    /**
     * Unmarks a task
     *
     * @param taskNo The index of the task
     */
    public static void unmarkTask(int taskNo) {
        tasks.get(taskNo).setDone(false);
    }

    /**
     * Deletes a task
     *
     * @param taskNo The index of the task
     */
    public static void deleteTask(int taskNo) {
        tasks.remove(taskNo);
    }

    /**
     * Adds a task
     *
     * @param task The task to be added
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a <code>List</code> of <code>Task</code> of the matching tasks. If a task description
     * contains the keyword, it is considered a match.
     *
     * @param keyword The keyword to be searched
     * @return Returns a <code>List</code> of <code>Task</code> of the matching tasks
     */
    public static List<Task> findTask(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (int taskNo = 0; taskNo < getNumberOfTasks(); taskNo++) {
            if (getTask(taskNo).getDescription().contains(keyword)) {
                matchedTasks.add(getTask(taskNo));
            }
        }
        return matchedTasks;
    }
}