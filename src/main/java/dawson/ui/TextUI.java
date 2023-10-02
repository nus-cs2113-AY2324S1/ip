package dawson.ui;

import java.util.Scanner;

public class TextUI {
    Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = scanner.nextLine().trim();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine;
    }

     /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        final String COMMENT_LINE_FORMAT_REGEX = "#.*";
        boolean isCommentLine = rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);

        return rawInputLine.trim().isEmpty() || isCommentLine;
    }

    /**
     * Print given input text together with a line as separator
     * 
     * @param text 
     */
    public static void printText(String text) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        System.out.println("\t " + text);
        System.out.println(line);
    }

    /**
     * Print string array of multi line text with a line as separator
     * 
     * @param texts multi line text
     */
    public static void printText(String[] texts) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        for (String text : texts) {
            System.out.println("\t " + text);
        }
        System.out.println(line);
    }

    public void printWelcomeText() {
        printText(Messages.MESSAGE_WELCOME);
    }
}
