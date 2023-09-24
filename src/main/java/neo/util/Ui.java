package neo.util;

import java.util.Scanner;

/**
 * Represents the methods to interact with the user. This class is abstract as its
 * main purpose is to provide methods relating to interaction with the user.
 */
public abstract class Ui {
    /**
     * Prints message that welcomes user whenever Neo chatbot is started up.
     */
    public static void welcomeMessage() {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints message that says bye to the user when /bye command is used.
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Return the input from the user.
     *
     * @return The input from the user in CLI.
     */
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
