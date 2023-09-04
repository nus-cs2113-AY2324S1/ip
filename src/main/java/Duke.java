import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    final private static String BOT_NAME = "Python";
    final private static int HORIZONTAL_LINE_LENGTH = 80;

    final private static Scanner in = new Scanner(System.in);

    private static void printHorizontalLine() {
        char[] horizontalLine = new char[HORIZONTAL_LINE_LENGTH];
        Arrays.fill(horizontalLine, '—');
        System.out.println(new String(horizontalLine));
    }
    
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.printf("Hello! I am a short Java Bot %s!\n", BOT_NAME);
        System.out.println("What can I do for you?");
        printHorizontalLine();

        String inputCommand;
        do {
            inputCommand = in.nextLine();
            printHorizontalLine();
            if (inputCommand.equals("bye")) {
                System.out.println("Bye. See you again when you run the program again!");
            } else {
                System.out.println(inputCommand);
            }
            printHorizontalLine();
        } while(!inputCommand.equals("bye"));
    }
}
