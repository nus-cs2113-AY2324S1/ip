package AMY;

import AMY.command.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    // Add a task to the list when an todo command is entered
    public static void addToList(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    // List all tasks when "list" command is entered
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    // Delete a task from the list when "delete" command is entered
    public static void deleteTask(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task removedTask = taskList.remove(taskIndex - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    // Unmark task as done when "unmark" command is entered
    public static void unmarkTask(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    // Mark task as done when "mark" command is entered
    public static void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    // Find tasks containing a specific keyword
    public static void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                System.out.println((++count) + ". " + task);
            }
        }

        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}
