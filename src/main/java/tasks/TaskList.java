package tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTaskIndex(int index) {
        return this.tasks.get(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone(true);
    }

    public void markTaskAsUndone(int index) {
        Task task = tasks.get(index);
        task.markAsDone(false);
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("ERROR: ☹ OOPS!!! No tasks is available.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
