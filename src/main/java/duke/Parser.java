package duke;


/**
 * A utility class responsible for parsing user commands.
 */
public class Parser {
    /**
     *
     * @param input Input array containing what the user types into command line
     * @return An array tha contains the parsed command and the arguments
     */
    public static String[] parseCommand(String input) {
        // Split the input into command and arguments
        return input.split(" ");
    }
}
