package bob.parser;

import bob.BobException;
import bob.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses Bob commands.
 */
public class BobParser {

    /**
     * Pattern used to parse Bob commands.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*?)");

    /**
     * Parses Bob command from {@param userInput}. Returns an Array of length 2 in the form
     * [commandWord, arguments].
     *
     * @param userInput command sent by user.
     * @return Array of length two containing [commandWord, arguments].
     */
    public Command parseCommand(String userInput) throws BobException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new BobException("Invalid command!");
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommandParser().parse(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);
        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            throw new BobException("I don't know that command.");
        }
    }

}
