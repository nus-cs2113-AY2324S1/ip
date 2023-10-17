package duke;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the Duke application.
 * It manages tasks using Java's ArrayList methods, providing functionality to access,
 * add, and remove tasks from the list.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTaskAt(int idx) {
        return tasks.get(idx);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTaskAt(int idx) {
        tasks.remove(idx);
    }   
}