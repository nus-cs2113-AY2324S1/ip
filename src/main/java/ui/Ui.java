package ui;

import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;

/**
 * Represents the user interface of the Duke application.
 */

public class Ui {
    private Scanner scanner;

    private static final String lineDivider = "____________________________________________________________";

    /**
     * Initializes the Ui with a new Scanner instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message to the user when the application starts.
     */
    public static void greet() {
        String logo = "  ____                  \n"
                + " |  _ \\  ___  _ __ ___  \n"
                + " | | | |/ _ \\| '_ ` _ \\ \n"
                + " | |_| | (_) | | | | | |\n"
                + " |____/ \\___/|_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        String greetings = lineDivider +
                "\nHello! I'm Dom\n" +
                "What can I do for you?\n" +
                lineDivider;
        System.out.println(greetings);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void goodbye() {
        String goodbye = lineDivider +
                "\nBye. Hope to see you again soon!\n" +
                lineDivider;
        System.out.println(goodbye);
    }

    /**
     * Displays an error message when there is a loading error.
     */
    public static void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Displays the specified error message to the user.
     * 
     * @param errorMsg The error message to display.
     */
    public void showErrorMsg(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Reads a command from the user.
     * 
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a line divider to the user.
     */
    public void showLineDivider() {
        System.out.println(lineDivider);
    }

    /**
     * Lists all tasks currently in the task list.
     * 
     * @param tasks The list of tasks to be displayed.
     */
    public void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message to the user when a task is marked as done.
     * 
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("OK, I've marked this task as done:\n" + " "
                + task.getStatusIcon() + " " + task.getDescription());
    }
    

    /**
     * Displays a message to the user when a task is marked as undone.
     * 
     * @param task The task that was marked as undone.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(" Nice! I've marked this task as not done:\n" + "   " + task.getStatusIcon()
        + " " + task.getDescription());
    }

    /**
     * Displays a message to the user when a task is deleted.
     * 
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public void showInvalidTaskNumberError() {
        System.out.println("Error: Invalid task number.");
    }
    
    /*
     * Displays a message to the user when a task is deleted.
     * 
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     * 
     */
    public void showEmptyTaskNumberError() {
        System.out.println("Error: ☹ OOPS!!! Task number cannot be empty\n");
        System.out.println("Please enter command in the format: unmark <task number>");
    }
    
    /*
     * Displays a message to the user when a task is deleted.
     * 
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     * 
     */
    public void showInvalidTaskNumberFormatError() {
        System.out.println("Error: ☹ OOPS!!! Task number must be an integer.\n");
        System.out.println("Please enter command in the format: unmark <task number>");
    }

    /**
     * Displays a message to the user when a task is added.
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after addition.
     */
    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message to the user when a task is deleted.
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message to the user when a task is found.
     * 
     * @param task The task that was found.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a message to the user when a task is found.
     * 
     * @param task The task that was found.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message to the user when a task is found.
     * 
     * @param task The task that was found.
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println(lineDivider);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + "." + foundTasks.get(i));
        }
    }
}
