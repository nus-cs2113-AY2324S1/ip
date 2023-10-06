package python.parser;

/**
 * Contains utility functions for parsing of raw input given by the user. All classes here are static
 */
public class Parser {
    /**
     * Returns the first word as command
     *
     * @param inputLine The raw user input
     * @return Returns the first word as command
     */
    public static String extractCommandFromInputLine(String inputLine) {
        return inputLine.split("\\s+")[0];
    }

    /**
     * Returns the rest of the sentence excluding the first word
     *
     * @param inputLine The raw user input
     * @return Returns the rest of the sentence excluding the first word
     */
    public static String extractCommandDescFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    /**
     * Returns the second word as integer
     *
     * @param inputLine The raw user input
     * @return Returns the second word as integer
     */
    public static int extractTaskNoFromInputLine(String inputLine) {
        return Integer.parseInt(inputLine.split(" ")[1]);
    }

    /**
     * Returns the second word as keyword
     *
     * @param inputLine The raw user input
     * @return Returns the second word as keyword
     */
    public static String extractKeywordFromInputLine(String inputLine) {
        return inputLine.split(" ")[1];
    }

    /**
     * Returns the rest of the sentence excluding the first word
     *
     * @param inputLine The raw user input
     * @return Returns the rest of the sentence excluding the first word
     */
    public static String extractTodoDescFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    /**
     * Returns the rest of the sentence excluding the first word
     *
     * @param inputLine The raw user input
     * @return Returns the rest of the sentence excluding the first word
     */
    public static String extractDeadlineDetailsFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    /**
     * Returns the text after the first word
     *
     * @param deadlineDetails The deadline details
     * @return Returns the text after the first word
     */
    public static String extractDeadlineDescFromDeadlineDetails(String deadlineDetails) {
        return deadlineDetails.split(" /by ")[0];
    }

    /**
     * Returns the text after the /by command
     *
     * @param deadlineDetails The deadline details
     * @return Returns the text after the /by command
     */
    public static String extractDeadlineByFromDeadlineDetails(String deadlineDetails) {
        return deadlineDetails.split(" /by ")[1];
    }

    /**
     * Returns the rest of the sentence excluding the first word
     *
     * @param inputLine The raw user input
     * @return Returns the rest of the sentence excluding the first word
     */
    public static String extractEventDetailsFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    /**
     * Returns the sentence before /from
     *
     * @param eventDetails The event details
     * @return Returns the sentence before /from
     */
    public static String extractEventDescFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[0];
    }

    /**
     * Returns the sentence after /from and before /to
     *
     * @param eventDetails The event details
     * @return Returns the sentence after /from and before /to
     */
    public static String extractEventFromFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[1];
    }

    /**
     * Returns the sentence after /to
     *
     * @param eventDetails The event details
     * @return Returns the sentence after /to
     */
    public static String extractEventToFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[2];
    }
}
