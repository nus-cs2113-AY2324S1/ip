package chattie;

import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Handles all list related functions like add, delete, etc.
 */
public class TaskList {

    private static ArrayList<Task> list;

    /**
     * Constructor that creates a new TaskList object with an empty array list
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor that creates a new TaskList object containing list items
     * loaded from chattie.txt
     *
     * @param list Array list of tasks obtained from chattie.txt
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public static int getSize() {
        return list.size();
    }

    /**
     * Returns the task list
     *
     * @return array list of tasks
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns the Task object at a specific index of the task list
     *
     * @param index Index of task to be retrieved
     * @return Task object at index
     */
    public static Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Adds a task to the task list
     *
     * @param task Task to be added
     */
    public static void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task at the specified location
     *
     * @param taskNum Position of the task to be deleted in the list
     * @throws ChattieException When taskNum is out of bounds
     */
    public static void delete(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }
        list.remove(taskNum);
    }

    /**
     * Marks a task at the specified location to be done
     *
     * @param taskNum Position of the task to be marked in the list
     * @throws ChattieException When taskNum is out of bounds
     */
    public static void mark(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }

        list.get(taskNum).setDone(true);
    }

    /**
     * Unmarks a task at the specified location to be not done
     *
     * @param taskNum Position of the task to be unmarked in the list
     * @throws ChattieException When taskNum is out of bounds
     */
    public static void unmark(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }

        list.get(taskNum).setDone(false);
    }

    /**
     * Finds tasks that matches the search query
     *
     * @param query String that the user wants to search
     * @return Array list of tasks that contains the query
     */
    public static ArrayList<Task> find (String query) {
        return (ArrayList<Task>) list.stream()
                .filter(task -> task.getTask().contains(query))
                .collect(Collectors.toList());
    }
}
