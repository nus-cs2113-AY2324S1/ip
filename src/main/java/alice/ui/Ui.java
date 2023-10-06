package alice.ui;

public class Ui {
    private static final String TAB_SPACE = "    ";

    /**
     * For printing the hello message
     */
    public static void printHelloMessage() {
        System.out.println("    Hi there! I am");
        String alice = "     / \\ / \\  / \\  / \\_/ \\\n    ( A )( L )( I )( C )( E )\n     \\_/ \\_/  \\_/  \\_/ \\_/\n";
        System.out.println(alice);
        System.out.println("    What can I do for you?\n");
    }

    /**
     * For printing the bye message
     */
    public static void printByeMessage() {
        System.out.println("    Bye for now... We will miss you:( See you again very soon!");
        System.out.println("       *****   ");
        System.out.println("     *       * ");
        System.out.println("    *  O   O  *");
        System.out.println("    *    ∆    *");
        System.out.println("     *       * ");
        System.out.println("       *****   ");
    }

    /**
     * For printing a line divider to separate commands
     */
    public static void printLineDivider() {
        System.out.println("\n____________________________________________________________\n");
    }

    /**
     * Print a message with 4 spaces in front
     * @param message to be printed
     */
    public static void printOneTabMessage(String message) {
        System.out.println(TAB_SPACE + message);
    }

    /**
     * Print a message with 8 spaces in front
     * @param message
     */
    public static void printTwoTabMessage(String message) {
        System.out.println(TAB_SPACE + TAB_SPACE + message);
    }

    /**
     * Print error message when there is a loading problem
     */
    public static void printLoadingError() {
        printOneTabMessage("There is a loading error!");
        printLineDivider();
    }

    /**
     * Print error message
     * @param errorMessage message to be printed
     */
    public static void printError(String errorMessage) {
        printOneTabMessage("Alice is malfunctioning, there has been an error..........");
        printOneTabMessage(errorMessage);
        printLineDivider();
    }

    /**
     * Print error message when there are missing or extra things in the user's input
     */
    public static void wrongInputError() {
        Ui.printOneTabMessage("You have an extra input OR you are missing an input!\n    CORRECT IT BEFORE THE KNAVE OF HEART COMES!\n");
        printLineDivider();
    }
}
