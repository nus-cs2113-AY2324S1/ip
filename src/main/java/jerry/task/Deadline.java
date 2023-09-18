package jerry.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jerry.exceptions.InvalidTaskFormatException;

public class Deadline extends Task {
    protected String by;

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Deadline format.";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.by);
    }

    public static Deadline fromString(String userInput) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("(.+) /by (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            return new Deadline(description, by);
        } else {
            throw new InvalidTaskFormatException(FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
