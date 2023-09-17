package jerry.task;

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

    @Override
    public String serialize() {
        return String.format("| E | %s | %s | %s | %s", this.getStatusInt(), this.getDescription(), this.from, this.to);
    }

    public static Event deserialize(String line) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("^| E | ([01]) | (.+) | (.+) | (.+)$");
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches() && matcher.groupCount() == 3) {
            String statusInt = matcher.group(1);
            String description = matcher.group(2);
            String from = matcher.group(3);
            String to = matcher.group(4);
            Event event = new Event(description, from, to);
            if (Integer.parseInt(statusInt) == 1) {
                event.markAsDone();
            }
            return event;
        } else {
            throw new InvalidTaskFormatException(FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
