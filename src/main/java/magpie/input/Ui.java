package magpie.input;

import magpie.exceptions.MagpieException;
import java.util.Scanner;
/**
 * Initializes a <code>scanner</code> list to get user input, contains methods to print messages
 * such as logo and bye, and parses input by calling <code>Parser</code> methods.
 */
public class Ui {

    private static final String LINE = "\n____________________________________________________________";

    private static Scanner scanInput;

    /**
     * Initializes <code>Scanner</code> for user input.
     */
    public Ui() {
        scanInput = new Scanner(System.in);
    }

    /**
     * Reads user input until <code>bye</code> is given and initializes a <code>Parser</code> to process each input.
     * Prints error messages if <code>MagpieException</code> is caught.
     */
    public static void processUserInput() {

        String input = "";
        input = scanInput.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            Parser userInput = new Parser(input);
            try {
                userInput.processCommand();
            } catch (MagpieException e) {
                System.out.println(e.getErrorMessage());
            }
            input = scanInput.nextLine();

        }
    }

    /**
     * Displays MAGPIE logo on execution.
     */
    public static void printLogo() {
        System.out.println(LINE);
        System.out.println(" __  __          _____ _____ _____ ______ ");
        System.out.println("|  \\/  |   /\\   / ____|  __ \\_   _|  ____|");
        System.out.println("| \\  / |  /  \\ | |  __| |__) || | | |__   ");
        System.out.println("| |\\/| | / /\\ \\| | |_ |  ___/ | | |  __|  ");
        System.out.println("| |  | |/ ____ \\ |__| | |    _| |_| |____ ");
        System.out.println("|_|  |_/_/    \\_\\_____|_|   |_____|______|");
        System.out.println("\nHello! I'm Magpie :)");
        System.out.println("What can I do for you?\n");
        System.out.println(LINE);
    }

    /**
     * Displays BYE message when program exits.
     */
    public static void printByeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
