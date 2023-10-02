package spaceman.data;

import static spaceman.ui.Messages.LINE;
import static spaceman.ui.Messages.MESSAGE_MARK;
import static spaceman.ui.Messages.MESSAGE_UNMARK;

import spaceman.data.task.Task;
import spaceman.ui.Ui;

import java.util.ArrayList;

/**
 * Contains the list of tasks.
 * Able to do operations such as adding tasks, deleting tasks, mark tasks as done and mark tasks as not done.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
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
     * Deletes a specific task based on the index.
     * @param taskIndex index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Task.decreaseTaskCountByOne();
        Ui.showTaskRemovedMessage(task);
    }

    /**
     * Marks a specific task as done based on the index.
     * @param taskIndex index of the task to be marked as done
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex-1).markTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_MARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
    }

    /**
     * Marks a specific task as not done based on the index.
     * @param taskIndex index of the task to be marked as not done
     */
    public void unMarkTask(int taskIndex) {
        tasks.get(taskIndex-1).unMarkTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_UNMARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
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
        System.out.println(LINE);
        if (isFound) {
            int resultCount = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : results) {
                System.out.println(resultCount + ". " + task.getDetails());
            }
        } else {
            System.out.println("No matching tasks are found :/");
        }
        System.out.println(LINE);
    }
}
