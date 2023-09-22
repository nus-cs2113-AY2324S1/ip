package zenbot;
import java.util.Scanner;

/**
 * Represents a class that handles user input.
 */
public class Input {
    private static Scanner input = new Scanner(System.in);
    private static String line = "";

    public static String getInput() {
        line = input.nextLine().trim();
        return line;
    }

}
