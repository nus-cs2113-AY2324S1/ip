package spaceman.ui;

import static spaceman.ui.Messages.LINE;
import static spaceman.ui.Messages.LOGO;
import static spaceman.ui.Messages.MESSAGE_BYE;

import spaceman.data.TaskList;
import spaceman.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface (UI) of the application.
 * This class is responsible for reading user input and displaying output to the user.
 * It provides methods for reading user input and printing messages to the screen.
 */
public class Ui {
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads user input.
     * @return command entered by user
     */
    public String getUserCommand() {
        return input.nextLine();
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows the welcome message upon starting the program.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Shows the goodbye message before the program terminates.
     */
    public static void showGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(MESSAGE_BYE);
        System.out.println(LINE);
    }

    public void showException(Exception e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    /**
     * Shows current number of tasks.
     */
    public static void showTaskCount( ) {
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    /**
     * Shows message after a task is successfully added.
     * @param task task being added
     */
    public static void showTaskAddedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDetails());
        showTaskCount();
        System.out.println(LINE);
    }

    /**
     * Shows message after a task is successfully removed.
     * @param task task being removed
     */
    public static void showTaskRemovedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getDetails());
        showTaskCount();
        System.out.println(LINE);
    }

    /**
     * Shows a list of current tasks to the user.
     * @param taskList list of tasks
     */
    public static void showTaskList(TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.getDetails());
        }
        System.out.println(LINE);
    }

    /**
     * Shows read data error message.
     */
    public static void showReadDataError() {
        System.out.println(LINE);
        System.out.println("No previous data found /:");
        System.out.println(LINE);
    }

}
