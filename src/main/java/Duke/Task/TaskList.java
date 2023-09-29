package Duke.Task;

import java.util.ArrayList;

/**
 * A data structure that is created to contain many tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNumTask() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }
}
