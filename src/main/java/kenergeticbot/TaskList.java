package kenergeticbot;

import kenergeticbot.task.Task;
import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    protected static ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<Task>();
    }
    public int getSize() {
        return taskList.size();
    }
    public Task getTask(int taskID) {
        return taskList.get(taskID);
    }
    public void removeTask(int taskID) {
        taskList.remove(taskID);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
