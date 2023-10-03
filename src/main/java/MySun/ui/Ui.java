package MySun.ui;

import MySun.data.TaskList;
import MySun.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner input;
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO = " ____               \n"
            + "| ___| _   _  ______ \n"
            + "| \\__ | | | || /--\\ |\n"
            + " \\___|| |_| || |  | |\n"
            + "/____/ \\__,_||_|  |_|\n";

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String getUserCommand() {
        return input.nextLine();
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void showGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showException(Exception e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    public static void showTaskCount( ) {
        System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
    }

    public static void showTaskAddedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDescription());
        showTaskCount();
        System.out.println(LINE);
    }

    public static void showTaskRemovedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getDescription());
        showTaskCount();
        System.out.println(LINE);
    }

    public static void showTaskMarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.getDescription());
        System.out.println(LINE);
    }

    public static void showTaskUnmarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.getDescription());
        System.out.println(LINE);
    }

    public static void showFindMessage(boolean isFound,ArrayList<Task> results) {
        System.out.println(LINE);
        if (isFound) {
            int resultCount = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : results) {
                System.out.println(resultCount + ". " + task.getDescription());
            }
        } else {
            System.out.println("No matching tasks are found :/");
        }
        System.out.println(LINE);
    }

    public static void showTaskList(TaskList taskList) {
        System.out.println(LINE);
        if (taskList.getTasks().isEmpty()) {
            System.out.println("There is no tasks currently ;)");
            System.out.println(LINE);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.getDescription());
        }
        System.out.println(LINE);
    }

    public static void showReadDataError() {
        System.out.println(LINE);
        System.out.println("No previous data found /:");
        System.out.println(LINE);
    }

}
