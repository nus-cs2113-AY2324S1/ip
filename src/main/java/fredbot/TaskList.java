package fredbot;

import fredbot.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
        int numTask = Task.getNumTask();
        Task.setNumTask(numTask + 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        Task.setNumTask(Task.getNumTask() - 1);
        return tasks.remove(index);
    }

    public void markTask(int index, boolean mark) {
        tasks.get(index).setDone(mark);
    }
}
