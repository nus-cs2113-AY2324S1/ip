package duke.tasks;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.util.Locale.US;

/**
 * The `Event` class represents a specific type of task in the Hilary robot that with start and end time, events.
 * It extends the `Task` class and includes information about the event's start and end times.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected DateTimeFormatter eventFormatter;

    /**
     * Constructs an `Event` task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        eventFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", US);
    }

    /**
     * Gets the start and end times of the event.
     *
     * @return A formatted string representing the start and end times of the event.
     */
    public String getEventTime() {
        return startTime + " to " + endTime;
    }

    /**
     * Overrides the `toString` method to provide a formatted representation of the `Event` task.
     *
     * @return A string representation of the `Event` task, including its description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(eventFormatter) + " to " + endTime.format(eventFormatter) + ")";
    }
}
