package jerry.task;

import java.util.ArrayList;
import java.util.List;

import jerry.task.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task getTaskByIndex(int number) {
        return this.tasks.get(number - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(String.format("\t%d. %s\n",i + 1, tasks.get(i)));
        }

        return stringBuilder.toString();
    }
}
