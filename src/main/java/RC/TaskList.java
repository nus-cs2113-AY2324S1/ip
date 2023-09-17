package RC;

import RC.task.Task;


import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum);
    }

    public int getSize() {
        return tasks.size();
    }
}
