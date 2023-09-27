package kenergeticbot;

import kenergeticbot.task.Task;

import java.util.ArrayList;

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
    public void remove(int taskID) {
        taskList.remove(taskID);
    }
    public String toString() {
        return "A";
    }

    public void add(Task task) {
        taskList.add(task);
    }
}
