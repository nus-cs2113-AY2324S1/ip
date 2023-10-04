/**
 * Deadline is a kind of task with a specific deadline.
 * It is a subclass of the Task class and includes methods to
 * retrieve and format the deadline.
 * <p>
 * Deadline objects can be created with a description and a deadline time.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package Duke.tasks;

import Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Locale.US;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected DateTimeFormatter deadlineFormatter;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        deadlineFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", US);
    }

    public String getEventTime(){
        return by + "";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(deadlineFormatter) + ")";
    }
}
