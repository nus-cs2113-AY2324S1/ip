package lemon.ui;

import lemon.task.Task;
import lemon.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static lemon.common.Messages.*;

/**
 * Utility class for handling user interactions in the Lemon chatbot.
 * This class manages the displaying of messages to users and receiving of input from users.
 */
public class UI {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a UI instance.
     */
    public UI() {
        this(System.in, System.out);
    }

    /**
     * Constructs a UI instance with the specified input stream and print stream.
     *
     * @param in InputStream to receive input.
     * @param out PrintStream to display output.
     */
    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays divider between commands.
     */
    public void displayDivider() {
        out.println(LEMON_DIVIDER);
    }

    /**
     * Displays specified messages
     *
     * @param message Message to be displayed.
     */
    public void displayMessage(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LINE_SEPARATOR));
        }
    }

    /**
     * Displays welcome message at the start of the Lemon chatbot.
     */
    public void displayWelcome() {
        displayMessage(LEMON_LOGO, MESSAGE_INTRODUCTION, MESSAGE_WELCOME, LEMON_START_AND_END);
    }

    /**
     * Displays exit message at the end of the Lemon chatbot.
     */
    public void displayExit() {
        displayMessage(MESSAGE_EXIT, LEMON_START_AND_END);
    }

    /**
     * Receives command as input from the user.
     *
     * @return Input string from the user.
     */
    public String getInput() {
        String input = in.nextLine();
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Display empty list message when the list is empty.
     */
    public void displayEmptyList() {
        displayMessage(MESSAGE_EMPTY_LIST);
    }

    /**
     * Display task data of the specified task list.
     *
     * @param tasks Task list to be displayed.
     */
    public void displayList(ArrayList<Task> tasks) {
        displayMessage(MESSAGE_DISPLAY_LIST);
        int taskSerialNo = 1;
        for (Task task : tasks) {
            System.out.println(taskSerialNo + ". " + task.toString());
            taskSerialNo++;
        }
    }

    /**
     * Display added message when a task is added to the list.
     *
     * @param task Task added to the list.
     * @param tasks Task list the task is added to.
     */
    public void displayAddedTask(Task task, TaskList tasks) {
        displayMessage(MESSAGE_ADDED_TASK);
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " fruitful tasks in your basket!");
    }

    /**
     * Display deleted message when a task is deleted from the list.
     *
     * @param task Task deleted from the list.
     * @param tasks Task list the task is deleted from.
     */
    public void displayDeletedTask(Task task, TaskList tasks) {
        displayMessage(MESSAGE_DELETED_TASK);
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " fruitful tasks in your basket!");
    }

    /**
     * Display marked message when a task is marked as done.
     *
     * @param task Task marked as done.
     */
    public void displayMarkedTask(Task task) {
        displayMessage(MESSAGE_MARKED_TASK);
        System.out.println("\t" + task.toString());
    }

    /**
     * Display unmarked message when a task is marked as not done.
     *
     * @param task Task marked as not done.
     */
    public void displayUnmarkedTask(Task task) {
        displayMessage(MESSAGE_UNMARKED_TASK);
        System.out.println("\t" + task.toString());
    }

    /**
     * Display help message with a list of valid commands.
     */
    public void displayHelp() {
        displayMessage(
                HELP_MESSAGE,
                HELP_LIST_MESSAGE, COMMAND_LIST_FORMAT,
                HELP_TODO_MESSAGE, COMMAND_TODO_FORMAT,
                HELP_DEADLINE_MESSAGE, COMMAND_DEADLINE_FORMAT,
                HELP_EVENT_MESSAGE, COMMAND_EVENT_FORMAT,
                HELP_MARK_MESSAGE, COMMAND_MARK_FORMAT,
                HELP_UNMARK_MESSAGE, COMMAND_UNMARK_FORMAT,
                HELP_DELETE_MESSAGE, COMMAND_DELETE_FORMAT,
                HELP_BYE_MESSAGE, COMMAND_BYE_FORMAT
        );
    }

    /**
     * Display error message when exceptions and errors are encountered.
     *
     * @param message Error message displayed to user.
     */
    public void displayError(String message) {
        System.out.println(message);
    }
}
