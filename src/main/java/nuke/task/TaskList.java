package nuke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public String mark(int idx) {
        Task task = TASKS.get(idx);
        task.setDone(true);
        return task.toString();
    }

    public String unmark(int idx) {
        Task task = TASKS.get(idx);
        task.setDone(false);
        return task.toString();
    }

    public String delete(int idx) {
        Task task = TASKS.remove(idx);
        return task.toString();
    }

    public int size() {
        return TASKS.size();
    }

    public String[] getTasks() {
        return TASKS.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public String[] getFormattedTasks() {
        return TASKS.stream()
                .map(Task::formatData)
                .toArray(String[]::new);
    }
}
