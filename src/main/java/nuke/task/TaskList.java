package nuke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> TASKS;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a list of tasks with all elements in {@code tasks}.
     *
     * @param tasks a list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Adds the task in the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        TASKS.add(task);
    }

    /**
     * Marks the task of the index as done.
     *
     * @param idx index of the task to be marked done
     */
    public String mark(int idx) {
        Task task = TASKS.get(idx);
        task.setDone(true);
        return task.toString();
    }

    /**
     * Marks the task of the index as not done.
     *
     * @param idx index of the task to be marked not done
     */
    public String unmark(int idx) {
        Task task = TASKS.get(idx);
        task.setDone(false);
        return task.toString();
    }

    /**
     * Deletes the task of the index.
     *
     * @param idx index of the task to be deleted
     */
    public String delete(int idx) {
        Task task = TASKS.remove(idx);
        return task.toString();
    }

    public String[] find(String keyword) {
        return TASKS.stream()
                .filter(task -> task.getName().contains(keyword))
                .map(Task::toString)
                .toArray(String[]::new);
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Returns string representations of all tasks.
     *
     * @return all tasks in form of {@link String}[].
     */
    public String[] getTasks() {
        return TASKS.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    /**
     * Returns all tasks in form of formatted manner,
     * which is used to save in file.
     *
     * @return all tasks in form of formatted manner.
     */
    public String[] getFormattedTasks() {
        return TASKS.stream()
                .map(Task::formatData)
                .toArray(String[]::new);
    }
}
