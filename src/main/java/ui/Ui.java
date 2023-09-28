package ui;

public class Ui {

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
     *
     */
    public static void printLineDivider() {
        System.out.println("\n____________________________________________________________\n");
    }

    public static void printMessage(String message) {
        System.out.println("    " + message);
        printLineDivider();
    }
}
