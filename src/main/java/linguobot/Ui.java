package linguobot;
import java.util.ArrayList;

public class Ui {

    public static void printLine() {
        System.out.println("===================================================");
    }


    /**
     * Prints the given text sandwiched by two horizontal lines
     * @param text Text to be printed
     */
    public static void printText(String text) {
        printLine();
        System.out.println(text);
        printLine();
    }

    /**
     * Prints the given text without the two horizontal lines
     * @param text Text to be printed
     */
    public static void printTextWithoutLine(String text) {
        System.out.println(text);
    }

    /**
     * Prints the given texts sandwiched by two horizontal lines
     * @param texts Texts to be printed in a list
     */
    public static void printMultipleText(String[] texts) {
        printLine();
        for (String text : texts) {
            System.out.println(text);
        }
        printLine();
    }
}
