package jerry.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jerry.exceptions.IllegalValueException;

public class Todo extends Task {

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Todo format.";
    private static final String DECODE_REGEX = "^T~(.)~(.+)";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String encode() {
        return String.format("T~%s~%s", this.getStatusIcon(), this.getDescription());
    }

    public static Task decode(String string) throws IllegalValueException {
        Pattern pattern = Pattern.compile(DECODE_REGEX);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String isTaskDoneStr = matcher.group(1);
            String description = matcher.group(2);
            Todo newTask = new Todo(description);
            if (isTaskDoneStr.equals("X")) {
                newTask.markAsDone();
            }
            return newTask;
        } else {
            throw new IllegalValueException("");
        }
    }
}
