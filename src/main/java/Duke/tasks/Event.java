package Duke.tasks;

import Duke.Task;

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
