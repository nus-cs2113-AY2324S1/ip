package utils;

import exception.DukeException;
import task.Task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) throws DukeException {
        if (task.getName().isEmpty()) {
            throw new DukeException("Task must have a name.");
        }
        tasks.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index > tasks.size() - 1) {
            throw new DukeException("Index provided is greater than size of list");
        } else if (index < 0) {
            throw new DukeException("Index provided must be greater than 0");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void updateTaskStatus(int index, boolean status) throws DukeException {
        if (index > tasks.size() - 1) {
            throw new DukeException("Index provided is greater than size of list");
        } else if (index < 0) {
            throw new DukeException("Index provided must be greater than 0");
        }
        Task task = tasks.get(index);
        task.setIsComplete(status);
    }
}
