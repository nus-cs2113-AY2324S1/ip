import java.util.Arrays;

public class Duke {
    final private static String BOT_NAME = "Python";
    final private static int HORIZONTAL_LINE_LENGTH = 80;

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
        System.out.println("Bye. See you again when you run the program again!");
        printHorizontalLine();
    }
}
