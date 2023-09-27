package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;
import static java.lang.System.in;

public class Ui {
    private final Scanner scanner;
    public static final String MESSAGE_SHOW_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    public static final String MESSAGE_MARK_AS_NOT_DONE = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_MARKED_AS_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASKS_IN_YOUR_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_ADDED_THIS_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_REMOVE_THIS_TASK = "Noted. I've removed this task:";

    public Ui() {
        this.scanner = new Scanner(in);
    }

    public static void printAddMessage(TaskList taskList) {
        showLine();
        System.out.println(MESSAGE_ADDED_THIS_TASK);
        System.out.println(taskList.getTask(taskList.getSize() - 1) + "\n");
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        showLine();
    }

    public static void printDeleteMessage(TaskList taskList, Task task) {
        showLine();
        System.out.println(MESSAGE_REMOVE_THIS_TASK);
        System.out.println(task);
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        showLine();
    }


    public static void printUnmarkMessage(Task task) {
        showLine();
        System.out.println(MESSAGE_MARK_AS_NOT_DONE);
        System.out.println(task);
        showLine();
    }

    public static void printMarkMessage(Task task) {
        showLine();
        System.out.println(MESSAGE_MARKED_AS_DONE);
        System.out.println(task);
        showLine();
    }

    public static void printListMessage(TaskList taskList) {
        showLine();
        System.out.println(MESSAGE_TASKS_IN_YOUR_LIST);
        System.out.println(taskList.toString());
        showLine();
    }

    public static void printByeMessage() {
        showLine();
        System.out.println(MESSAGE_BYE);
        showLine();
    }

    public static void showLine() {
        System.out.println("-----------------------------------------------------");
    }
    public String getUserCommand() {
        return scanner.nextLine();
    }

    public static void showWelcome() {
        Ui.showLine();
        System.out.println("Hello! I'm ListWhisper");
        System.out.println("What can I do for you?");
        Ui.showLine();
    }

    public static void showError(Exception e) {
        System.out.println(e);
    }
}
