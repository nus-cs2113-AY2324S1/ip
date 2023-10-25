/**
 * The Parser class provides methods for parsing user input and command-specific input.
 */
public class Parser {
    /**
     * Parses the user input into an array of words.
     *
     * @param line The user input to be parsed.
     * @return An array containing the words in the input.
     */
    public static String[] parseUserInput(String line) {
        return line.split(" ", 2);
    }

    /**
     * Parses command-specific input based on the given command type.
     *
     * @param command The type of command (e.g., "deadline", "event", etc.).
     * @param input   The input specific to the command.
     * @return An array containing parsed information based on the command.
     */
    public static String[] parseCommandInput(String command, String input) {
        String[] words = null;  // Initialize the array
        switch (command) {
            case "deadline":
                words = input.split("/by");
                words[0] = words[0].trim();
                words[1] = words[1].trim();
                break;
            case "event":
                int startIndexOfFrom = input.indexOf("/from");
                int startIndexOfTo = input.indexOf("/to");
                String from = input.substring(startIndexOfFrom + 5, startIndexOfTo);
                String to = input.substring(startIndexOfTo + 4);
                String description = input.substring(0, startIndexOfFrom);
                words = new String[]{from.trim(), to.trim(), description.trim()};
                break;
            case "todo":
                words = new String[]{input.trim()};
                break;
            case "delete":
                break;
            case "bye":
                break;
            case "list":
                break;
            case "mark":
                break;
            case "unmark":
                break;
            default:
                // Handle the default case, if needed
                break;
        }
        return words;  // Return the array
    }

}

