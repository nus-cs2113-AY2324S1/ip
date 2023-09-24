package Storage;

import Soccat.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage taskFile) throws IOException {
        tasks.add(task);
        taskFile.setTaskData(tasks);
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

    public Task removeTask(int taskIndex, Storage taskFile) throws IOException, IndexOutOfBoundsException {
        taskFile.setTaskData(tasks);
        return tasks.remove(taskIndex);
    }

    public Task markTask(int taskIndex, boolean isDone, TaskList tasks, Storage taskFile)
            throws IOException, IndexOutOfBoundsException{
        Task task = tasks.getTask(taskIndex);
        if (task.getDone() == isDone) {
            return task;
        }
        task.setDone(isDone);
        taskFile.setTaskData(tasks.getTasks());
        return task;
    }

}
