package MySun.data;

import MySun.data.task.Task;
import MySun.ui.Ui;

import java.util.ArrayList;

/**
 * Contains the list of tasks.
 * Execute adding tasks, deleting tasks, mark tasks as done and unmark tasks.
 */
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

    /**
     * Adds a task to the list of tasks.
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.showTaskAddedMessage(task);
    }

    /**
     * Deletes a task.
     * @param taskIndex index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Ui.showTaskRemovedMessage(task);
    }

    /**
     * Marks a task as done.
     * @param taskIndex index of the task to be marked as done
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex-1).markAsDone();
        Ui.showTaskMarkMessage(tasks.get(taskIndex-1));
    }

    /**
     * UnMarks a task as done.
     * @param taskIndex index of the task to be unMarked
     */
    public void unMarkTask(int taskIndex) {
        tasks.get(taskIndex-1).markAsNotDone();
        Ui.showTaskUnmarkMessage(tasks.get(taskIndex-1));
    }

    /**
     * Finds a list of tasks that contains the specified keyword provided by the user.
     * @param keyword keyword to be searched
     */
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
