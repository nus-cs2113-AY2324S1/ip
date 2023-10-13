package ui;

import task.Task;
import task.TaskList;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.in;

/**
 * Ui class in charge of printing out user facing messages.
 * All messages are stored as constant
 */
public class Ui {
    private final Scanner scanner;
    public static final String HELLO_MESSAGE = "Hello! I'm ListWhisper";
    public static final String WHAT_CAN_I_DO_FOR_YOU = "What can I do for you?";
    public static final String MATCHING_TASKS = "Here are the matching tasks in your list:";
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

    /**
     * Print message when add todo, event or deadline is called
     * @param taskList taskList for current user
     */
    public static void printAddMessage(TaskList taskList) {
        showLine();
        System.out.println(MESSAGE_ADDED_THIS_TASK);
        System.out.println(taskList.getTask(taskList.getSize() - 1) + "\n");
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        showLine();
    }

    /**
     * Print message when delete command is requested
     *
     * @param taskList taskList for current user
     * @param task deleted task
     */
    public static void printDeleteMessage(TaskList taskList, Task task) {
        showLine();
        System.out.println(MESSAGE_REMOVE_THIS_TASK);
        System.out.println(task);
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        showLine();
    }

    /**
     * Print message when unmark is called
     *
     * @param task task unmarked
     */
    public static void printUnmarkMessage(Task task) {
        showLine();
        System.out.println(MESSAGE_MARK_AS_NOT_DONE);
        System.out.println(task);
        showLine();
    }

    /**
     * Print message when mark is called
     *
     * @param task task marked
     */
    //print message when mark is called
    public static void printMarkMessage(Task task) {
        showLine();
        System.out.println(MESSAGE_MARKED_AS_DONE);
        System.out.println(task);
        showLine();
    }

    /**
     * Print message when list command is called
     *
     * @param taskList taskList of current user
     */
    public static void printListMessage(TaskList taskList) {
        showLine();
        System.out.println(MESSAGE_TASKS_IN_YOUR_LIST);
        System.out.println(taskList.toString());
        showLine();
    }

    /**
     * Print goodbye message when exit is called
     */
    public static void printByeMessage() {
        showLine();
        System.out.println(MESSAGE_BYE);
        showLine();
    }

    /**
     * Print list of matching tasks when find is called
     * @param matchingTasks an array list of matching tasks
     */
    public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println(MATCHING_TASKS);
        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(i + "."  + matchingTasks.get(i - 1));
        }
    }

    /**
     * Print hello message when the app starts
     */
    public static void showWelcome() {
        showLine();
        System.out.println(HELLO_MESSAGE);
        System.out.println(WHAT_CAN_I_DO_FOR_YOU);
        showLine();
    }

    /**
     * Print error message
     *
     * @param e exception
     */
    public static void showError(Exception e) {
        System.out.println(e);
    }

    /**
     * Print a line-separator
     *
     */
    public static void showLine() {
        System.out.println("-----------------------------------------------------");
    }

    /**
     * read user command from command line input
     *
     * @return user command
     */
    public String getUserCommand() {
        return scanner.nextLine();
    }
}
