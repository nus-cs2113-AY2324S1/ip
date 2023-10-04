package jerry.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jerry.exceptions.InvalidTaskFormatException;
import jerry.exceptions.IllegalValueException;

public class Event extends Task {
    private String from;
    private String to;

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Event format.";
    private static final String DECODE_REGEX = "^E~(.)~(.*)~(.*)~(.*)";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatusIcon(), this.getDescription(), this.getFrom(), this.getTo());
    }

    @Override
    public String encode() {
        return String.format("E~%s~%s~%s~%s", this.getStatusIcon(), this.getDescription(), this.getFrom(), this.getTo());
    }

    public static Event decode(String string) throws IllegalValueException {
        Pattern pattern = Pattern.compile(DECODE_REGEX);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches() && matcher.groupCount() == 4) {
            String isTaskDoneStr = matcher.group(1);
            String description = matcher.group(2);
            String from = matcher.group(3);
            String to = matcher.group(4);
            Event newTask = new Event(description, from, to);
            if (isTaskDoneStr.equals("X")) {
                newTask.markAsDone();
            }
            return newTask;
        } else {
            throw new IllegalValueException("ehllooo");
        }
    }
}
