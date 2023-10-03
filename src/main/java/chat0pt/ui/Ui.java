package chat0pt.ui;

import chat0pt.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles the formatting and printing of the output and the reading of user input.
 */
public class Ui {
    private Scanner scanner;
    private final String LINE = "_______________________________________________________________________";

    /**
     * Constructor for the Ui class
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints output that it receives from other methods and wraps it with the start and end lines.
     * @param toPrint Takes in a String[] and prints it line by line
     */
    public void print(String[] toPrint) {
        System.out.println(LINE);
        for (String str : toPrint) {
            System.out.println(str);
        }
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message
     */
    public void welcomeMessage() {
        String[] welcome = {"Hello! I'm Chat0PT!", "What can I do for you?"};
        print(welcome);
    }

    /**
     * Prints the goodbye message
     */

    public void goodbyeMessage() {
        String[] goodbye = {"Bye. Hope to see you again soon!"};
        print(goodbye);
    }

    /**
     * Prints an invalid task message.
     * @param action Type of task that was trying to be added
     */
    public void invalidMessage(String action) {
        print(new String[]{"☹ OOPS!!! The description of a " + action + " cannot be empty or the format is incorrect."});
    }

    /**
     * Executes when a task is successfully added to the ArrayList
     * @param t Task to add
     * @param arrayListSize Current size of the ArrayList
     */
    public void successfulTask(Task t, int arrayListSize) {
        String[] successPrint = {"Got it. I've added this task:", t.toString(), "Now you have " + arrayListSize + " tasks in your list."};
        print(successPrint);
    }

    /**
     * Prints when the user enters an invalid command
     */
    public void unsupportedCommand() {
        String[] unsupported = {"Sorry! I do not support this command at this point. I can only add Deadline, Event and Todo!"};
        print(unsupported);
    }

    /**
     * Prints when the user enters an invalid number or no number when trying to delete a task
     */
    public void invalidDelete() {
        String[] message = {"☹ Unable to delete task! Check if the format or task number is correct."};
        print(message);
    }

    /**
     * Delete Message
     * @param t Task that was removed
     * @param size Remaining number of tasks in the ArrayList
     */
    public void deleteMessage(Task t, int size) {
        size -= 1;
        String[] message = {"Noted. I've removed this task: ", t.toString(), "Now you have " + size + " tasks in the list."};
        print(message);
    }

    /**
     * Invalid message when a user enters an invalid number to mark.
     */
    public void invalidMark() {
        String[] message = {"Please check your mark input. It can only be a number and it has to exist in the list."};
        print(message);
    }

    /**
     * Successful mark message
     * @param t Task that was marked or not marked
     * @param toMark If true, the task has been marked as done, else if false, the task has been marked as not done
     */
    public void mark(Task t, boolean toMark) {
        String[] message = {"", t.toString()};
        if (toMark) {
            message[0] = "Nice! I've marked this task as done:";
        } else {
            message[0] = "OK, I've marked this task as not done yet:";
        }
        print(message);
    }

    /**
     * Standardised ArrayList printing format (used for list and find commands)
     * @param tasks The tasklist for the program to print
     */
    public void listHandler(ArrayList<Task> tasks) {
        int count = 1;
        String[] taskStrings = new String[tasks.size() + 1];
        taskStrings[0] = "Here are the tasks/matching tasks in your list: ";
        for (Task t : tasks) {
            taskStrings[count] = count + ". " + t.toString();
            count++;
        }
        print(taskStrings);
    }

    /**
     * Prints when the program is unable to access the file
     */
    public void failedFile() {
        String[] message = {"Failed to access file. This session of Chat0PT would not be saved"};
        print(message);
    }

    /**
     * Reads user input.
     * @return Returns the input line
     */

    public void failedFind(){
        String[] message = {"We are unable to find any results. Try using list to see all tasks instead or ensure your command is in the format find <keyword>."};
        print(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
