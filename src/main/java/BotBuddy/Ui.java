package BotBuddy;

import java.util.Scanner;

/**
 * The Ui class contains methods to print messages to the user and get input from the user
 *
 */
public class Ui {
    public void printUnderscores() {
        System.out.println("____________________________________________________________");
    }

    public void printCreateTaskFileMsg() {
        printUnderscores();
        System.out.println("Task file not found! Creating one...");
        printUnderscores();
    }

    public void printCreateTaskFileErrorMsg() {
        printUnderscores();
        System.out.println("Error creating task file... Exiting!");
        printUnderscores();
    }

    public void printTaskFileWriteErrorMsg() {
        printUnderscores();
        System.out.println("Error writing to file!");
        printUnderscores();
    }

    public void printWelcomeMsg() {
        printUnderscores();
        System.out.println("Hello from BotBuddy!");
        System.out.println("What can I do for you?");
        printUnderscores();
    }

    public void printToUser(String msg) {
        printUnderscores();
        System.out.println(msg);
        printUnderscores();
    }

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();
        return input;
    }

    public void printExitMsg() {
        printUnderscores();
        System.out.println("Goodbye, hope to see you again soon!");
        printUnderscores();
    }
}


