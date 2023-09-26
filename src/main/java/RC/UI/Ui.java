package RC.UI;

import java.util.Scanner;

/**
 * Represents user interactions and handles displaying messages and receiving input.
 */
public class Ui {
    private static final String MESSAGE_WELCOME = "\tHello! I'm RC\n\tWhat can I do for you?\n";
    private static final String MESSAGE_EXIT = "\tBye. Hope to see you again soon!\n";

    public Ui() {
    }

    /**
     * Displays message to user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcomeMessage() {
        showMessage(MESSAGE_WELCOME);
    }

    /**
     * Displays an exit message to the user.
     */
    public void exitMessage() {
        showMessage(MESSAGE_EXIT);
    }

    /**
     * Receives the user input and removes leading and trailing spaces.
     *
     * @return The user's input without leading and trailing spaces.
     */
    public String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
}
