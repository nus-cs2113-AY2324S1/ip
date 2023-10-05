/**
 * This class represents the user interface (UI) for the Ken chatbot.
 * It provides methods for displaying welcome and goodbye messages, as well
 * as a horizontal line separator for a cleaner UI presentation.
 */
public class Ui {

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public static void printWelcomeMessage() {
        printKEN();
        System.out.println("  Hello! I'm Ken");
        System.out.println("  What would you like to do?");
    }

    /**
     * Displays the goodbye message when the chatbot is exiting.
     */
    public static void printGoodbyeMessage() {
        System.out.println("Bye for now!");
    }

    /**
     * Prints a horizontal line separator to separate different sections of the output.
     */
    public static void printLine() {
        System.out.println(" ____________________________________________________________");
    }

    // Private Helper Method

    /**
     * Prints the ASCII art representation of the chatbot's name "KEN."
     */
    private static void printKEN() {
        System.out.println("  K   K  EEEEE  N   N");
        System.out.println("  K  K   E      NN  N");
        System.out.println("  KKK    EEEE   N N N");
        System.out.println("  K  K   E      N  NN");
        System.out.println("  K   K  EEEEE  N   N");
    }
}
