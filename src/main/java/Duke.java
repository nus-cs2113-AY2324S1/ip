import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            printHorizontalLine();
            System.out.println("    " + input);
            printHorizontalLine();
        }

        printFarewell();
    }

    public static void printIntroduction() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Careo");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    public static void printFarewell() {
        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("    " + "-".repeat(76));
    }
}
