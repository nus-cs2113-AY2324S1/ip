package mudmud.task;

import mudmud.parser.Parser;

import java.time.LocalDateTime;

/**
 * Represents a deadline in the list.
 */
public class Deadline extends Task{
    protected LocalDateTime by;
    private Parser parser;

    /**
     * Creates a new todo.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        parser = new Parser();
    }

    /**
     * Creates a deadline from the previous session.
     *
     * @param description The description of the task.
     * @param isDone Status of whether it is marked or unmarked.
     * @param by The due date of the task.
     */
    public Deadline(String description, String isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
        parser = new Parser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String byString = parser.convertDateTimetoString(by);
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
