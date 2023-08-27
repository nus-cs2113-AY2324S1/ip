import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<String>();
    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("list")) {
                int counter = 1;

                printHorizontalLine();
                for (String task : tasks) {
                    System.out.println("    " + counter + ". " + task);
                    counter++;
                }
                printHorizontalLine();
            } else if (input.equals("bye")) {
                break;
            } else {
                printHorizontalLine();
                tasks.add(input);
                System.out.println("    added: " + input);
                printHorizontalLine();
            }
        }

        printFarewell();
    }

    private static void printIntroduction() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Careo");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    private static void printFarewell() {
        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    " + "-".repeat(76));
    }
}
