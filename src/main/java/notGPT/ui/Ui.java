package notGPT.ui;

import java.util.Scanner;

/**
 * Represents the user interface interactions for the notChatGPT application.
 * Supports CLI interactions with the user.
 */
public class Ui {
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object

    /**
     * Displays a line separator for a cleaner user interface.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays an introductory message when the application starts.
     */
    public void displayIntroMessage() {
        showLine();
        System.out.println(
                "Hello! I'm notChatGPT :)\n" +
                        "What can I do for you?");
        showLine();
    }

    /**
     * Reads and retrieves user input from the console.
     *
     * @return The user's input as a String.
     */
    public String getUserInput() {
        String userInput = input.nextLine();
        return userInput;
    }
}

