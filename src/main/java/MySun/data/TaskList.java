package MySun.data;

import MySun.data.task.Task;
import MySun.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.showTaskAddedMessage(task);
    }

    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Ui.showTaskRemovedMessage(task);
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex-1).markAsDone();
        Ui.showTaskMarkMessage(tasks.get(taskIndex-1));
    }

    public void unMarkTask(int taskIndex) {
        tasks.get(taskIndex-1).markAsNotDone();
        Ui.showTaskUnmarkMessage(tasks.get(taskIndex-1));
    }

    public void findTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword.toLowerCase())) {
                isFound = true;
                results.add(task);
            }
        }
        Ui.showFindMessage(isFound, results);
    }
}
