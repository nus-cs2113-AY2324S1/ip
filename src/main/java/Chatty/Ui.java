/**
 * The Ui class handles any input or output of the chatbot
 */
package Chatty;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________";

    /**
     * prints the welcome message
     */
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Chatty!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * gets the user input
     * @return user input
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * prints the goodbye message
     */
    public void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Shows error message when failed to load tasks from .txt file
     */
    public void showLoadingError() {
        System.out.println("Error loading or creating data file.");
    }

    /**
     * prints any message
     * @param message message to print
     */
    public void printMessage(String message){
        System.out.println(message);
    }
}