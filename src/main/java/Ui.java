import Tasks.*;
import java.util.ArrayList;

/**
 * The Ui class manages user interface interactions and messages.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Outputs a header for the task list.
     */
    public static void outputHeader() {
        printLine();
        System.out.println("Here are the matching tasks in your list:\n");
    }

    /**
     * Prints a visual line separator.
     */
    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    /**
     * Shows an error message when loading tasks from a file.
     */
    public static void showLoadingError() {
        System.out.println("Error loading tasks from text file to Dukey");
    }

    public static void todoEmptyInputError() {
        System.out.println("Oops! I'm sorry, but description cannot be empty.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
    }

    public static void todoFormatError() {
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
    }

    public static void eventEmptyInputError() {
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }

    public static void eventFormatError() {
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }


    public static void deadlineEmptyInputError() {
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    public static void deadlineFormatError() {
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    public static void unrecognizedCommandError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input  " +
                "one of the available commands in its specific format\n" +
                "Commands: todo, event, deadline, delete, find, mark, unmark");
        printLine();
    }

    /**
     * Displays the task list to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        printLine();
    }

}

