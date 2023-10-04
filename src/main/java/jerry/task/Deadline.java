package jerry.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jerry.exceptions.InvalidTaskFormatException;
import jerry.exceptions.IllegalValueException;

/**
 * Represents a task of type deadline in the task list.
 */
public class Deadline extends Task {
    private String by;

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid Deadline format.";
    private static final String DECODE_REGEX = "^D~(.)~(.*)~(.*)";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.getBy());
    }

    @Override
    public String encode() {
        return String.format("D~%s~%s~%s", this.getStatusIcon(), this.getDescription(), this.getBy());
    }

    /**
     * Create the adequate task according to the encode string
     *
     * @parameter the encoded string
     * @returns the task
     */
    public static Deadline decode(String string) throws IllegalValueException {
        Pattern pattern = Pattern.compile(DECODE_REGEX);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches() && matcher.groupCount() == 3) {
            String isTaskDoneStr = matcher.group(1);
            String description = matcher.group(2);
            String by = matcher.group(3);
            Deadline newTask = new Deadline(description, by);
            if (isTaskDoneStr.equals("X")) {
                newTask.markAsDone();
            }
            return newTask;
        } else {
            throw new IllegalValueException("ehllooo");
        }
    }
}
