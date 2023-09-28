/**
 * The Ui class handles user interface-related functions, such as displaying messages.
 */
public class Ui {

    /**
     * Prints a welcome message to the console.
     * This message is displayed when the Duke application starts.
     */
    public static void printWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm En\nWhat can I do for you?");
        printLines();
    }

    /**
     * Prints a line of equal signs to the console for visual separation.
     * This method is used for formatting and visual clarity.
     */
    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}
