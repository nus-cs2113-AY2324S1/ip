import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    final private static String BOT_NAME = "Python";
    final private static int HORIZONTAL_LINE_LENGTH = 80;

    final private static String pythonASCIIArt =
            "\t ____        _   _                 \n" +
            "\t|  _ \\ _   _| |_| |__   ___  _ __  \n" +
            "\t| |_) | | | | __|  _ \\ / _ \\|  _ \\ \n" +
            "\t|  __/| |_| | |_| | | | (_) | | | |\n" +
            "\t|_|    \\__, |\\__|_| |_|\\___/|_| |_|\n" +
            "\t       |___/                        ";

    final private static String pythonEmoji = "\uD83D\uDC0D";
    final private static Scanner in = new Scanner(System.in);

    private static void printHorizontalLine() {
        char[] horizontalLine = new char[HORIZONTAL_LINE_LENGTH];
        Arrays.fill(horizontalLine, '—');
        System.out.println("\t" + new String(horizontalLine));
    }
    
    public static void main(String[] args) {
        System.out.println(pythonASCIIArt);
        printHorizontalLine();
        System.out.printf("\t%s: Hello! I am a short Java Bot %s!\n", pythonEmoji, BOT_NAME);
        System.out.printf("\t%s: What can I do for you?\n", pythonEmoji);
        printHorizontalLine();

        String inputCommand;
        do {
            inputCommand = in.nextLine();
            printHorizontalLine();
            if (inputCommand.equals("bye")) {
                System.out.printf("\t%s: Bye. See you again when you run the program again!\n", pythonEmoji);
            } else {
                System.out.printf("\t%s: %s\n", pythonEmoji, inputCommand);
            }
            printHorizontalLine();
        } while(!inputCommand.equals("bye"));
    }
}
