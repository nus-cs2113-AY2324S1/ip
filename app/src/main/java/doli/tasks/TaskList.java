package doli.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * <h3>TaskList class</h3>
 * The TaskList class implements the iterable interface and primarily
 * serves to create a personalised version of an ArrayList which can be
 * used to create the agenda Doli will manage.
 * <p>
 * <b>Note:</b> Objects of type TaskList can also be used as streams for easier implementation and filtering.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    /**
     * Constructs an object of type TaskList with a specifiable agenda.
     *
     * @param taskList an ArrayList of Tasks which will form the core of the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an object of type TaskList with an empty agenda.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param numberOfTaskToDelete of type int specifying the index of the task to be deleted (starting from 0).
     */
    public void deleteTask(int numberOfTaskToDelete) {
        taskList.remove(numberOfTaskToDelete - 1);
    }

    /**
     * Removes all tasks from the TaskList by resetting it to an empty one.
     */
    public void deleteAll() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a specific task to the TaskList.
     *
     * @param taskToAdd of type Task to be added to the agenda.
     */
    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    /**
     * Gets the number of Tasks contained withing the TaskList.
     *
     * @return the size of the agenda.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets a specific task contained within the agenda.
     *
     * @param numberOfTaskToGet of type int referring to the index of the task to retrieve.
     * @return the requested task.
     */
    public Task getTask(int numberOfTaskToGet) {
        return taskList.get(numberOfTaskToGet - 1);
    }

    /**
     * Allows for iteration across the elements of the TaskList.
     *
     * @return an iteration throughout the Tasks contained within the agenda.
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    /**
     * Allows to treat the TaskList as a stream and perform quick and efficient actions upon it.
     *
     * @return a Stream of Tasks.
     */
    public Stream<Task> stream() {
        return taskList.stream();
    }

    /**
     * Overrides a general object's method toString() in order to allow a more specialized String representation.
     * In this case it will e.g. loop over the entries of the agenda and concatenate them effectively using line breaks.
     *
     * @return a String comprising a neat overview of the agenda.
     */
    @Override
    public String toString() {
        StringBuilder agenda = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            agenda.append(String.format("   %d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return agenda.toString();
    }
}
