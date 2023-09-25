package data;

import static ui.Messages.LINE;
import static ui.Messages.MESSAGE_MARK;
import static ui.Messages.MESSAGE_UNMARK;
import static ui.Messages.MESSAGE_EMPTY_TODO;
import static ui.Messages.MESSAGE_EMPTY_DEADLINE;
import static ui.Messages.MESSAGE_EMPTY_EVENT;

import data.exception.IncompleteDescriptionException;
import data.task.Deadline;
import data.task.Event;
import data.task.Task;
import data.task.Todo;

import ui.Ui;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    public void addTask(Task task) {
        tasks.add(task);
        Ui.printTaskAddedMessage(task);
    }

    /**
     * Deletes a specific task based on the index.
     * @param taskIndex index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Task.decreaseTaskCountByOne();
        Ui.printTaskRemovedMessage(task);
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

    public void findTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
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
