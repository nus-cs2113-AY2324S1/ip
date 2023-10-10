package fredbot;

import fredbot.task.Task;

import java.util.ArrayList;

/**
 * Represent a class that reference a list of task object
 * used by the user of FredBot
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    /**
     * Adds a task to the tasks array list and increment the task count
     * @param task the new task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        int numTask = Task.getNumTask();
        Task.setNumTask(numTask + 1);
    }

    /**
     * Returns a task from the tasks array list given the index
     * @param index the index at which the task should be retrieved
     * @return task object that is from the tasks array list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a task that is removed from the tasks array list given the index
     * @param index the index at which the task should be removed
     * @return task object that is removed from the tasks array list
     */
    public Task removeTask(int index) {
        Task.setNumTask(Task.getNumTask() - 1);
        return tasks.remove(index);
    }

    /**
     * Marsk a task as done or undone according to the boolean provided
     * @param index the index at which the task should be marked
     * @param mark boolean where true means task should be marked and false means task should be unmark
     */
    public void markTask(int index, boolean mark) {
        tasks.get(index).setDone(mark);
    }
}
