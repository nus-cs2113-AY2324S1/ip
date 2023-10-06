package luke.user;

import java.util.Scanner;

/**
 * The Ui Class is responsible for user interaction, including reading user input and displaying messages.
 */
public class Ui {
    //directly interact with user (read in (to Parser)/print)
    public String echo;
    public Scanner userInput;

    /**
     * Constructs a Ui object and initializes the user input scanner.
     */
    public Ui () {
        userInput = new Scanner(System.in);
    }

    /**
     * Closes the user input scanner.
     */
    public void closeUi() {
        userInput.close();
    }

    /**
     * Displays a welcome message with a logo when the application starts.
     */
    public void showWelcome() {
        String logo = "\t _           _        \n"
                + "\t| |    _   _| | _____ \n"
                + "\t| |   | | | | |/ / _ \\\n"
                + "\t| |___| |_| |   <  __/\n"
                + "\t|_____|\\__,_|_|\\_\\___|\n";

        System.out.println("\t" + "Hello! I'm\n" + logo);
        System.out.println("\tWhat can I do for you?");
    }

    /**
     * Displays a divider line to separate different sections of output.
     */
    public void showLine() {
        // show the divider line ("_______")
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays an error message for loading issues.
     */
    public void showLoadingError() {
        System.out.println("\tError in loading memory.");
    }

    /**
     * Displays an error message for loading issues.
     */
    public void showNoMemoryFileError() {
        System.out.println("\tNo memory is loaded.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads a command from the user and returns it as a string.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        echo = userInput.nextLine();
        return echo;
    }
}
