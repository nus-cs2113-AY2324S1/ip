package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Extracts the command and argument from the input string provided by the user.
     * @param input user input describing the command and argument
     * @return Command object containing the extracted command and argument
     */
    public static Command extractCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = null;
        if (parts.length > 1) {
            argument = parts[1];
        }
        return new Command(command, argument);
    }
}

