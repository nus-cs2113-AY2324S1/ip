/**
 * Event is a task that has a specific start and end time.
 * It is a subclass of the Task class and includes methods to
 * retrieve and format the event time.
 *
 * <p>
 * Event objects can be created with a description, start time,
 * and end time.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package duke.tasks;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Locale.US;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected DateTimeFormatter eventFormatter;
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        eventFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", US);

    }

    public String getEventTime(){
        return startTime + " to " + endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + startTime.format(eventFormatter) + " to " + endTime.format(eventFormatter) + ")";
    }
}
