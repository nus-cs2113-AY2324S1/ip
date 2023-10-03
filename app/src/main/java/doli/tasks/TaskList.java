package doli.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import doli.tasks.Task;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public void deleteTask(int numberOfTaskToDelete) {
        taskList.remove(numberOfTaskToDelete - 1);
    }
    public void deleteAll() {
        taskList = new ArrayList<>();
    }
    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }
    public int getSize() {
        return taskList.size();
    }
    public Task getTask(int numberOfTaskToGet) {
        return taskList.get(numberOfTaskToGet - 1);
    }
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
    public Stream<Task> stream() {
        return taskList.stream();
    }
    @Override
    public String toString() {
        StringBuilder agenda = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            agenda.append(String.format("   %d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return agenda.toString();
    }
}
