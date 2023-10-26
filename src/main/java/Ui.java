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


}

