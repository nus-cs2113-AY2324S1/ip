package elgin.ui;

/**
 * UI class that handles formatting of program output.
 */
public class Ui {

    final String BOT_NAME;

    final String SEPARATOR = "____________________________________________________________";

    /**
     * Constructor for UI.
     * Initialise BOT_NAME.
     */
    public Ui() {
        BOT_NAME = "Elgin";
    }

    /**
     * Prints a greeting message when the program first started.
     */
    public void sayGreeting() {
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    /**
     * Prints a message when the user exit the program.
     */
    public void sayBye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the array of strings in a standardised format.
     *
     * @param lines Array of strings to be printed.
     */
    public void formatPrint(String[] lines) {
        System.out.println("\t" + SEPARATOR);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t" + SEPARATOR);
    }

    /**
     * Prints in a standardised format.
     *
     * @param line The string to be printed.
     */
    public void formatPrint(String line) {
        System.out.println("\t" + SEPARATOR);
        System.out.println("\t" + line);
        System.out.println("\t" + SEPARATOR);
    }

    /**
     * Format and print an error message when
     * the user supplied an unknown command.
     */
    public void showUnknownCmdErrorMsg() {
        formatPrint("Sorry I do not understand your command");
    }

}
