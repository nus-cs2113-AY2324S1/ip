package userinputs;

import commands.Commands;
import taskmanagement.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println(UserMessages.WELCOME_MESSAGE.message);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String errorMessage) {
        showLine();
        System.out.println(errorMessage);
        showLine();
    }

    public static void showLine() {
        System.out.println(UserMessages.LINE.message);
    }

    public void showGoodbye() {
        System.out.println(UserMessages.GOODBYE_MESSAGE.message);
    }

    public void showLoadingError() {
        System.out.println(UserMessages.LOADING_ERROR.message);
    }

    public static void showHelp(){
        showLine();
        System.out.println(UserMessages.HELP_MESSAGE.message);
        showLine();
    }


    public static void echo(ArrayList<Task> items, Task task, String input) {
        String action = input.startsWith(Commands.DELETE_TASK_COMMAND) ? "deleted" : "added";
        String statusMessage = input.startsWith(Commands.DELETE_TASK_COMMAND) ? "" : " [" + task.getStatusIcon() + "]";
        String output = "Noted! Task " + action + ": " + task.getDescription() + statusMessage + "\n" +
                "    Number of Tasks: " + items.size();

        printAndEcho(output, items);
    }

    // to echo after a task's status is changed
    public static void echo(Task task, boolean isDone) {
        String action = isDone ? "marked as done" : "unmarked";
        String output = isDone ? "Congrats! :D Task " + action + ": " + task.getDescription() + " [" + task.getStatusIcon() + "]"
                : "Oopsies! Task " + action + ": " + task.getDescription() + " [" + task.getStatusIcon() + "]";

        printAndEcho(output);
    }

    // to echo when LIST command is being used
    public static void echo(ArrayList<Task> items) {
        showLine();
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
        showLine();
        System.out.println(output);
        showLine();
    }

    private static void printAndEcho(String output, ArrayList<Task> items) {
        printAndEcho(output);
        echo(items);
    }


}
