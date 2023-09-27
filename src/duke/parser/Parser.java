package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses Duke commands.
 */
public class Parser {

    /**
     * Pattern used to parse Duke commands.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*?)");

    /**
     * Parses Duke command from {@param userInput}. Returns an Array of length 2 in the form
     * [commandWord, arguments].
     *
     * @param userInput command sent by user.
     * @return Array of length two containing [commandWord, arguments].
     */
    public String[] parseCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        matcher.matches();

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        return new String[] {commandWord, arguments};
    }

}
