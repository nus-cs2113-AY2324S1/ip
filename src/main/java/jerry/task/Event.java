package jerry.task;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jerry.exceptions.InvalidTaskFormatException;

public class Event extends Task {
    protected String from;
    protected String to;

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Event format.";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s (from: %s to: %s)", this.getStatusIcon(), this.getDescription(), this.from, this.to);
    }

    public static Event fromString(String userInput) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.matches() && matcher.groupCount() == 3) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new Event(description, from, to);
        } else {
            throw new InvalidTaskFormatException(FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
