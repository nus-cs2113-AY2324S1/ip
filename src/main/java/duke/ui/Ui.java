package duke.ui;

import static duke.ui.MessageConstants.LINE;
import static duke.ui.MessageConstants.LOGO;
import static duke.ui.MessageConstants.MESSAGE_WELCOME;
import static duke.ui.MessageConstants.MESSAGE_GOODBYE;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles the user interface of Duke.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);
    private static PrintStream printer = System.out;

    /**
     * Prints the welcome message.
     */
    public static void showWelcome() {
        printer.println(LINE);
        printer.println(MESSAGE_WELCOME);
        printer.println(LOGO);
    }

    /**
     * Prints the goodbye message.
     */
    public static void showGoodbye() {
        printer.println(LINE);
        printer.println();
        printer.println(MESSAGE_GOODBYE);
        printer.println(LINE);
    }

    /**
     * Prints the given message.
     *
     * @param message The message to be printed.
     */
    public static void showMessage(String message) {
        printer.println(LINE);
        printer.println();
        printer.println(message);
        printer.println();
    }

    /**
     * Reads the next line of input.
     *
     * @return The next line of input.
     */
    public static String readCommand() {
        printer.println(LINE);
        printer.println();
        printer.print("Enter command: ");
        return sc.nextLine();
    }
}