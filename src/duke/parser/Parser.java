package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*?)");

    public String[] parseCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        matcher.matches();

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        return new String[] {commandWord, arguments};
    }

}
