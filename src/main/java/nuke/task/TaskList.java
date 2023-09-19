package nuke.task;

import nuke.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public String mark(int idx) {
        Task task = tasks.get(idx);
        task.setDone(true);
        return task.toString();
    }

    public String unmark(int idx) {
        Task task = tasks.get(idx);
        task.setDone(false);
        return task.toString();
    }

    public String delete(int idx) {
        Task task = tasks.remove(idx);
        return task.toString();
    }

    public void addTodo(String name) {
        add(new Todo(name));
    }

    public void addDeadline(String name, String by) {
        add(new Deadline(name, by));
    }

    public void addEvent(String name, String from, String to) {
        add(new Event(name, from, to));
    }

    public int size() {
        return tasks.size();
    }

    public String[] getTasks() {
        return tasks.stream().map(Task::toString).toArray(String[]::new);
    }

    public String[] getFormattedTasks() {
        return tasks.stream().map(Task::formatData).toArray(String[]::new);
    }
}
