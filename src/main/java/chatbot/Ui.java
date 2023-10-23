package chatbot;

import java.util.ArrayList;

import tasks.Task;

/**
 * This is the user interface class.
 * It handles the printing of all cosmetics and user messages.
 * Each type of user message is implemented in its own public method
 */

public class Ui {
    public void printDivider() {
        System.out.println("------------------------------------");
    }

    public void printWelcomeMessage() {
        printDivider();
        System.out.println("Welcome to TheChattyFatty chatbot!");
        printDivider();
    }

    public void printGoodbyeMessage() {
        printDivider();
        System.out.println("Goodbye!");
        printDivider();
    }

    public void printNotFoundMessage() {
        printDivider();
        System.out.println("Task not found.");
        printDivider();
    }

    public void printFoundMessage() {
        printDivider();
        System.out.println("Found the above tasks.");
        printDivider();
    }

    public void printListMessage() {
        printDivider();
        System.out.println("Here are your results:");
    }

    public void printNewTaskMessage() {
        printDivider();
        System.out.println("Successfully created:");
    }

    public void printDeleteMessage() {
        printDivider();
        System.out.println("Successfully deleted:");
    }

    public void printTask(Task t) {
        t.show();
    }
}
