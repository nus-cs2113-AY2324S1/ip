package ui;

import static ui.Messages.LINE;
import static ui.Messages.LOGO;
import static ui.Messages.MESSAGE_BYE;

import data.TaskList;
import data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String getUserCommand() {
        return input.nextLine();
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public static void showGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(MESSAGE_BYE);
        System.out.println(LINE);
    }

    public static void showTaskCount( ) {
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    public static void showTaskAddedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDetails());
        showTaskCount();
        System.out.println(LINE);
    }

    public static void showTaskRemovedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getDetails());
        showTaskCount();
        System.out.println(LINE);
    }

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

    public void showReadDataError() {
        System.out.println(LINE);
        System.out.println("No previous data found /:");
        System.out.println(LINE);
    }

}
