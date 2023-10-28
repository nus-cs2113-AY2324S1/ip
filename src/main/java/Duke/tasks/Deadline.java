package duke.tasks;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Locale.US;

/**
 * The `Deadline` class represents a specific type of task in the Hilary robot, tasks with a deadline.
 * It extends the `Task` class and includes information about the deadline.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected DateTimeFormatter deadlineFormatter;

    /**
     * Constructs a `Deadline` task with the given description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        deadlineFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", US);
    }

    /**
     * Gets the deadline time of the task.
     *
     * @return The deadline time.
     */
    public String getEventTime(){
        return by + "";
    }

    /**
     * Overrides the `toString` method to provide a formatted representation of the `Deadline` task.
     *
     * @return A string representation of the `Deadline` task, including its description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(deadlineFormatter) + ")";
    }
}
