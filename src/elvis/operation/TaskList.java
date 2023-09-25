package elvis.operation;

import elvis.task.Task;
import elvis.task.ToDo;
import elvis.task.Deadline;
import elvis.task.Event;
import java.util.ArrayList;

/**
 * Stores all the tasks when program is running
 */
public class TaskList {
    /**
     * An ArrayList that keeps track of all Task instances.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();   //Keeps track of all Task Instances made
    /**
     * Retrieves the ArrayList of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public static ArrayList<Task> getArray() {
        return tasks;
    }

    /**
     * Gets the size of the ArrayList of tasks.
     *
     * @return The size of the ArrayList.
     */
    public static int getArraySize() {
        return tasks.size();
    }

    /**
     * Adds a ToDo task to the ArrayList.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public static void addToDo(String description, int isDone) {
        tasks.add(new ToDo(description, isDone));
    }

    /**
     * Adds a Deadline task to the ArrayList.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param byWhen The deadline of the task.
     */
    public static void addDeadline(String description, int isDone, String byWhen) {
        tasks.add(new Deadline(description, isDone, byWhen));
    }

    /**
     * Adds an Event task to the ArrayList.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime The end date and time of the event.
     */
    public static void addEvent(String description, int isDone, String startDateTime, String endDateTime) {
        tasks.add(new Event(description, isDone, startDateTime, endDateTime));
    }

    /**
     * Removes a task from the ArrayList.
     *
     * @param nthTask The index of the task to be removed.
     */
    public static void taskRemover(int nthTask) {
        tasks.remove(nthTask);
    }

    /**
     * Retrieves a task from the ArrayList.
     *
     * @param nthTask The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public static Task getTask(int nthTask) {
        return tasks.get(nthTask);
    }

    /**
     * Retrieves the type of a task from the ArrayList.
     *
     * @param nthTask The index of the task whose type is to be retrieved.
     * @return The type of the task at the specified index.
     */
    public static char getTaskType(int nthTask) {
        return tasks.get(nthTask).getTaskType();
    }

    /**
     * Retrieves the status of a task from the ArrayList.
     *
     * @param nthTask The index of the task whose status is to be retrieved.
     * @return The status of the task at the specified index.
     */
    public static String getTaskStatus(int nthTask) {
        return tasks.get(nthTask).getStatus();
    }

    /**
     * Sets the status of a task in the ArrayList.
     *
     * @param nthTask The index of the task whose status is to be set.
     * @param isDone The new status of the task.
     */
    public static void setTaskStatus(int nthTask, boolean isDone) {
        tasks.get(nthTask).setStatus(isDone);
    }

    /**
     * Retrieves the description of a task from the ArrayList.
     *
     * @param nthTask The index of the task whose description is to be retrieved.
     * @return The description of the task at the specified index.
     */
    public static String getTaskDescription(int nthTask) {
        return tasks.get(nthTask).getDescription();
    }

    /**
     * Saves the current list of tasks to the storage file.
     */
    public static void saver() {
        Storage.saver(tasks);
    }

    /**
     * Checks if the ArrayList of tasks is empty.
     *
     * @return true if the ArrayList is empty, false otherwise.
     */
    public static boolean isArrayEmpty() {
        return tasks.isEmpty();
    }
}
