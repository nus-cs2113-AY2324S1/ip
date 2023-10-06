package Cara;

import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interaction.
 * It provides methods to read user input and display messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs an Ui object with a Scanner for reading user input.
     */
    public Ui() {

        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a welcome message when the program starts.
     */
    public void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm");
        System.out.println(" ________  ________  ________  ________     \n" +
                "|\\   ____\\|\\   __  \\|\\   __  \\|\\   __  \\    \n" +
                "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
                " \\ \\  \\    \\ \\   __  \\ \\   _  _\\ \\   __  \\  \n" +
                "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\n" +
                "                                            ");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message when the program exits.
     */
    public void printByeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Throws a DukeException with an error message for loading errors.
     *
     * @throws CaraException If there is an error loading tasks from a file.
     */
    public void showLoadingError() throws CaraException {
        throw new CaraException("Error reading from file. Unable to load tasks.");
    }

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
