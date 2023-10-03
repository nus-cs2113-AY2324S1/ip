package dude;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Ui` class handles user interface interactions, including displaying messages,
 * reading user input, and formatting output for the Dude task manager.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new `Ui` instance with a scanner for reading user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Draws a horizontal line of underscores to visually separate sections of output.
     */
    public void drawLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Displays a greeting message and the Dude logo when the application starts.
     */
    public void showGreeting() {
        String logo = "###            #        \n"
                + "#  #           #        \n"
                + "#  #  #  #   ###   ##   \n"
                + "#  #  #  #  #  #  # ##  \n"
                + "#  #  #  #  #  #  ##    \n"
                + "###    ###   ###   ##   \n";

        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm your best dude:)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showFarewell() {
        drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    /**
     * Displays a general message to the user with a horizontal line separator.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Displays an error message with an "OOPS!!!" prefix and a horizontal line separator.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        drawLine();
        System.out.println("☹ OOPS!!! " + message);
        drawLine();
    }

    /**
     * Displays a list of tasks with their descriptions and numbers.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(ArrayList<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        drawLine();
    }

    /**
     * Displays a message confirming the addition of a new task and the current total
     * number of tasks in the list.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks after adding the new task.
     */
    public void showAddedTask(Task task, int taskCount) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
        drawLine();
    }

    /**
     * Displays a message confirming the removal of a task and the current total
     * number of tasks in the list.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks after removing the task.
     */
    public void showRemovedTask(Task task, int taskCount) {
        drawLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
        drawLine();
    }

    /**
     * Displays a message confirming the marking of a task as done or not done,
     * along with the task's status.
     *
     * @param task   The task that was marked.
     * @param isDone A boolean indicating whether the task is marked as done or not done.
     */
    public void showMarkedTask(Task task, boolean isDone) {
        drawLine();
        String message = isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        System.out.println(message);
        System.out.println("   " + task);
        drawLine();
    }

    /**
     * Reads a command entered by the user from the console and returns it as a string.
     *
     * @return The user's input command.
     */
    public void showFoundTasks(ArrayList<Task> matchingTasks) {
        drawLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        drawLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
