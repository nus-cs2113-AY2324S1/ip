package duke;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the user interface of Duke.
 * It handles user input and displays messages to the user.
 */
public class Ui {
    private Scanner scanner;

    //Creates a new Ui instance with a Scanner for user input.
    public Ui() {
        scanner = new Scanner(System.in);
    }

    //Displays the welcome message when Duke is started.
    public void showWelcome() {
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                + "Hello I'm MatinBot\n"
                + "What can I do for you?\n"
                + LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    //Displays an error message when tasks cannot be loaded from a file.
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    //Displays a horizontal line to separate sections of the user interface.
    public void showLine() {
        System.out.println("__________________________________________");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    //Displays a goodbye message when exiting Duke
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! The list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String taskInfo = (i + 1) + ". " + task.toString();
                System.out.println(taskInfo);
            }
        }
    }

    /**
     * Displays a list of matching tasks to the user.
     *
     * @param matchingTasks The list of matching tasks to be displayed.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("☹ OOPS!!! No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskCount
                + " tasks in the list.");
    }

    /**
     * Displays a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Displays a message confirming the marking of a task as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void showTaskMarkedUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task      The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        if (task != null) {
            System.out.println("Noted. I've removed this task:\n" + task.toString());
        }
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}