package duke.ui;

import java.util.Scanner;

/**
 * Reads in user input and displays output.
 */
public class Ui {

    private final Scanner in;

    private static final String LINE = "____________________________________________________________";

    private static final String BOB = "\t   ___    ___  ___ \n"
            + "\t  / __\\  /___\\/ __\\\n"
            + "\t /__\\// //  //__\\/\\\n"
            + "\t/ \\/  \\/ \\_// \\/  \\\n"
            + "\t\\_____/\\___/\\_____/\n";

    /**
     * Constructs a new Ui.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prepends tab character to {@param line} and prints.
     *
     * @param line contents to print.
     */
    public void println(String line) {
        System.out.println("\t" + line);
    }

    /**
     * Prints a line separator for aesthetic.
     */
    public void printLine() {
        println(LINE);
    }

    /**
     * Prints greeting for when the application is first started.
     */
    public void printWelcome() {
        printLine();
        println("Hello! I'm\n" + BOB);
        println("What can I do for you?");
        printLine();
    }

    /**
     * Prints farewell when user exits the application.
     */
    public void printFarewell() {
        println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints output of command.
     *
     * @param result output of command.
     */
    public void printCommandResult(String result) {
        println(result);
    }

    /**
     * Prints error message for when application is unable to load Duke save file.
     */
    public void printLoadingError() {
        println("Unable to find file. Defaulting to empty list...");
    }

    /**
     * Reads in user input.
     *
     * @return Returns read line of user input.
     */
    public String getCommand() {
        return in.nextLine();
    }
}
