package Storage;

import Soccat.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskListLength() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex);
    }

    public Task removeTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.remove(taskIndex);
    }
}
