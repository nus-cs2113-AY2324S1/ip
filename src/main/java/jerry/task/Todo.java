package jerry.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jerry.exceptions.InvalidTaskFormatException;

public class Todo extends Task {

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Todo format.";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }

    public static Todo fromString(String userInput) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("(.+)");
        Matcher matcher = pattern.matcher(userInput);
        matcher.find();

        if (matcher.matches() && matcher.groupCount() == 1) {
            String description = matcher.group(1);
            return new Todo(description);
        } else {
            throw new InvalidTaskFormatException(FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
