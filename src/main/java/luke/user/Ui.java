package luke.user;

import java.util.Scanner;

/**
 * The Ui Class is responsible for user interaction, including reading user input and displaying messages.
 */
public class Ui {
    //directly interact with user (read in (to Parser)/print)
    public String echo;
    public Scanner userInput;

    private String logo = "\t _           _        \n"
            + "\t| |    _   _| | _____ \n"
            + "\t| |   | | | | |/ / _ \\\n"
            + "\t| |___| |_| |   <  __/\n"
            + "\t|_____|\\__,_|_|\\_\\___|\n";

    private String userGuide = "\n\tQuick guide to using Luke...\n" +
            "\t\tlist\n" +
            "\t\ttodo <description>\n" +
            "\t\tdeadline <description> /by <date>\n" +
            "\t\tevent <description> /from <start date> /to <end date>\n" +
            "\t\tmark <task number>\n" +
            "\t\tunmark <task_number>\n" +
            "\t\tdelete <task number>\n" +
            "\t\tfind <keyword>\n" +
            "\t\tbye\n";

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
        System.out.println("\t" + "Hello! I'm\n" + logo);
        System.out.println("\tWhat can I do for you?");

        System.out.print(userGuide);
        showLine();
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
