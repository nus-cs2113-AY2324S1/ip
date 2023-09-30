package ui;

import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;

public class Ui {
    private Scanner scanner;

    private static final String lineDivider = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void goodbye() {
        String goodbye = lineDivider +
                "\nBye. Hope to see you again soon!\n" +
                lineDivider;
        System.out.println(goodbye);
    }

    public static void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void showErrorMsg(String errorMsg) {
        System.out.println(errorMsg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLineDivider() {
        System.out.println(lineDivider);
    }

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

    public void showTaskMarked(Task task) {
        System.out.println("OK, I've marked this task as done:\n" + " "
                + task.getStatusIcon() + " " + task.getDescription());
    }
    

    public void showUnmarkedTask(Task task) {
        System.out.println(" Nice! I've marked this task as not done:\n" + "   " + task.getStatusIcon()
        + " " + task.getDescription());
    }

    public void showInvalidTaskNumberError() {
        System.out.println("Error: Invalid task number.");
    }
    
    public void showEmptyTaskNumberError() {
        System.out.println("Error: ☹ OOPS!!! Task number cannot be empty\n");
        System.out.println("Please enter command in the format: unmark <task number>");
    }
    
    public void showInvalidTaskNumberFormatError() {
        System.out.println("Error: ☹ OOPS!!! Task number must be an integer.\n");
        System.out.println("Please enter command in the format: unmark <task number>");
    }

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

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
