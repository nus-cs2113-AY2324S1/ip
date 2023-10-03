package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTime() {
        String startTimeStr;
        String endTimeStr;
        startTimeStr = startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        endTimeStr = endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return startTimeStr + " | " + endTimeStr;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to " + endTime + ")";
    }
}
