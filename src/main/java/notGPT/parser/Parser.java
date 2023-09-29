package notGPT.parser;

/**
 * The Parser class is responsible for parsing user commands.
 */
public class Parser {
    
    /**
     * Parses a user command into an array of strings based on space as the delimiter.
     *
     * @param command The user command to be parsed.
     * @return An array of strings containing the parsed command components.
     */
    public String[] parseCommand(String command) {
        return command.split(" ");
    }
}
