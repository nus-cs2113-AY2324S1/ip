package userinputs;

import commands.Commands;
import taskmanagement.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class for the Zran application to handle user's input and output.
 * Handles user interaction, displays messages, and reads user input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a UI instance with a Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println(UserMessages.WELCOME_MESSAGE.message);
        showLine();
    }

    /**
     * Receives the user  input.
     *
     * @return The user's input into Zran.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        //showLine();
        System.out.println(errorMessage);
        showLine();
    }

    /**
     * Displays a line separator.
     */
    public static void showLine() {
        System.out.println(UserMessages.LINE.message);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(UserMessages.GOODBYE_MESSAGE.message);
    }

    /**
     * Displays an error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println(UserMessages.LOADING_ERROR.message);
    }

    /**
     * Displays the help message.
     */
    public static void showHelp(){
        //showLine();
        System.out.println(UserMessages.HELP_MESSAGE.message);
        showLine();
    }

    /**
     * Outputs to user after adding or deleting a task.
     *
     * @param items The list of tasks.
     * @param task  The task that was added or deleted.
     * @param input The user's input into Zran.
     */
    public static void echo(ArrayList<Task> items, Task task, String input) {
        String action = input.startsWith(Commands.DELETE_TASK_COMMAND) ? "deleted" : "added";
        String statusMessage = input.startsWith(Commands.DELETE_TASK_COMMAND) ? "" : " [" + task.getStatusIcon() + "]";
        String output = "    Noted! Task " + action + ": " + task.getDescription() + statusMessage + "\n" +
                "    Number of Tasks: " + items.size();

        printAndEcho(output, items);
    }

    /**
     * Outputs to user after marking or unmarking a task.
     *
     * @param task   The task that was marked or unmarked.
     * @param isDone True if the task is marked as done, false otherwise.
     */
    public static void echo(Task task, boolean isDone) {
        String action = isDone ? "marked as done" : "unmarked";
        String output = isDone ? "    Congrats! :D Task " + action + ": " + task.getDescription() + " [" + task.getStatusIcon() + "]"
                : "    Oopsies! Task " + action + ": " + task.getDescription() + " [" + task.getStatusIcon() + "]";

        printAndEcho(output);
    }

    /**
     * Outputs the list of tasks to users.
     *
     * @param items The list of tasks to display.
     */
    public static void echo(ArrayList<Task> items) {
        //showLine();
        System.out.println("    List of Tasks:");
        int index = 0;
        for (Task item : items) {
            if (item != null) {
                System.out.print("    " + ++index + ". ");
                System.out.println(item.toString());
            }
        }
        showLine();
    }

    private static void printAndEcho(String output) {
        //showLine();
        System.out.println(output);
        showLine();
    }

    private static void printAndEcho(String output, ArrayList<Task> items) {
        printAndEcho(output);
        echo(items);
    }


}
