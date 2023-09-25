package ui;

import data.TaskList;
import data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    public static final String LINE = "------------------------------------------------------------";
    public static final String LOGO = "  ____  _____   ___    _____ _____ __    __   ___   __   __\n"
            + "/     /|  __ \\ /   \\  /   __|     |  \\  /  | /   \\ |  \\ |  |\n"
            + "\\   __\\| |__) |  _  \\|   /  |   __|   \\/   |/  _  \\|   \\|  |\n"
            + " \\__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |\n"
            + "/      | |   |   _   |   \\__|     |   __   |   _   |       |\n"
            + "|____ /|_|   |__| |__|\\_____|_____|__|  |__|__| |__|__|\\___|\n";

    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(MESSAGE_BYE);
        System.out.println(LINE);
    }

    public String getUserCommand() {
        return input.nextLine();
    }

    public static void printTaskCount( ) {
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDetails());
        printTaskCount();
        System.out.println(LINE);
    }

    public static void printTaskRemovedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getDescription());
        printTaskCount();
        System.out.println(LINE);
    }

    public static void printList(TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.getDetails());
        }
        System.out.println(LINE);
    }

    public void printReadDataError() {
        System.out.println(LINE);
        System.out.println("No previous data found /:");
        System.out.println(LINE);
    }

}
