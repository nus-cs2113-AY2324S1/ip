package elvis.task;

import elvis.operation.DateTimeHandler;
import java.time.LocalDateTime;

/**
 * Represents a Deadline task, which is a type of Task.
 * A Deadline task has a description, a completion status, and a deadline time.
 */
public class Deadline extends Task {

    /**
     * Constant representing the type of the task.
     * For Deadline tasks, this is always 'D'.
     */
    private final char taskType = 'D';

    /**
     * The deadline time for the task.
     */
    private LocalDateTime dateTime;

    /**
     * Constructs a new Deadline instance.
     *
     * @param description    The description of the Deadline task.
     * @param isDoneFromFile The completion status read from a file.
     * @param byWhen         The deadline time for the task.
     */
    public Deadline(String description, int isDoneFromFile, String byWhen) {
        super(description, isDoneFromFile);
        this.dateTime = DateTimeHandler.dateTimeParser(byWhen);
    }

    /**
     * Retrieves the type of the task.
     *
     * @return 'D' indicating that this is a Deadline task.
     */
    @Override
    public char getTaskType() {
        return taskType;
    }

    /**
     * Retrieves the deadline time of the task.
     *
     * @return LocalDateTime object representing the deadline time.
     */
    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
