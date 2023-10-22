package bob.parser;

import bob.BobException;
import bob.commands.EventCommand;
import bob.event.Event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommandParser implements Parser<EventCommand>{

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<description>.+)"
            + " /from (?<start>[^ ]+)"
            + " /to (?<end>[^ ]+)");

    @Override
    public EventCommand parse(String input) throws BobException {
        final Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new BobException("Format of command is incorrect.");
        }

        final String description = matcher.group("description").trim();
        final String start = matcher.group("start").trim();
        final String end = matcher.group("end").trim();

        Event newEvent = new Event(description, start, end);
        return new EventCommand(newEvent);
    }
}
