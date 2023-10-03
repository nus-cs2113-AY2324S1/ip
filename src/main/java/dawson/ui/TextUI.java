package dawson.ui;

import java.util.Scanner;

import dawson.command.CommandResult;

public class TextUI {

    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "\t_______________________________________________________" + LS;

    Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * 
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

    public void printCommandResult(CommandResult result) {
        printText(result.getMessageStrings());
    }

    /**
     * Prints one or more lines of text, surrounded by a divider, to the user console.
     *
     * @param texts The lines of text to be printed.
     */
    public void printText(String... texts) {
        System.out.println(DIVIDER);

        for (String text : texts) {
            System.out.println("\t " + text);
        }
        
        System.out.println(DIVIDER);
    }

    /**
     * Shows welcome message to user
     */
    public void printWelcomeText() {
        printText(Messages.MESSAGE_WELCOME);
    }
}
